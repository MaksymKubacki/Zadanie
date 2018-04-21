package zadanie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zadanie.bean.SessionManager;
import zadanie.entity.User;
import zadanie.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository ur;

	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("user", new User());
		return "user/addUser";
	}

	@PostMapping("/add")
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "user/addUser";
		}

		List<User> users = this.ur.findAll();
		for (User u : users) {
			if (u.getNickname().equals(user.getNickname())) {
				m.addAttribute("msg", "This nickname address is already used. Try different nickname.");
				return "user/addUser";
			}
		}
		this.ur.save(user);
		HttpSession s = SessionManager.session();
		s.setAttribute("user", user);
		return "redirect:/list";
	}

	@GetMapping("/change")
	public String changeUser(Model m) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		m.addAttribute("user", u);
		return "change";
	}

	@PostMapping("/change")
	public String changePost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		}
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		user.setId(u.getId());
		this.ur.save(user);
		return "redirect:/";
	}

	@GetMapping("/delete")
	public String delete(Model m) {
		HttpSession s = SessionManager.session();
		User u = (User) s.getAttribute("user");
		m.addAttribute("user", u);
		return "delete";
	}

	@GetMapping("/delete/{dec}")
	public String deletePost(@PathVariable int dec) {
		if (dec == 1) {
			HttpSession s = SessionManager.session();
			User u = (User) s.getAttribute("user");
			s.invalidate();
			this.ur.delete(u);
			return "redirect:/login";
		}
		return "redirect:/";
	}

}