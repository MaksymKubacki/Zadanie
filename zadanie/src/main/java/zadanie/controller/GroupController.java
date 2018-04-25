package zadanie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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
import zadanie.entity.Group;
import zadanie.repository.GroupRepository;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private GroupRepository gr;

	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("group", new Group());
		return "/group/addGroup";
	}

	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute Group group, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "/group/addGroup";
		}

		List<Group> groups = this.gr.findAll();
		for (Group g : groups) {
			if (g.getName().equals(group.getName())) {
				m.addAttribute("msg", "This group name address is already used. Try different name.");
				return "/group/addGroup";
			}
		}
		this.gr.save(group);
		HttpSession s = SessionManager.session();
		s.setAttribute("group", group);
		return "redirect:/group/list";
	}

	@GetMapping("/{id}/edit")
	public String changeGroup(Model m) {
		HttpSession s = SessionManager.session();
		Group g = (Group) s.getAttribute("group");
		m.addAttribute("group", g);
		return "/group/groupUpdate";
	}

	@PostMapping("/{id}/edit")
	public String changePost(@Valid @ModelAttribute Group group, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/group/list";
		}
		HttpSession s = SessionManager.session();
		Group g = (Group) s.getAttribute("group");
		group.setId(g.getId());
		this.gr.save(group);
		return "redirect:/group/list";
	}

	@GetMapping("/{id}/del")
	public String deletePost(@PathVariable int dec) {
			HttpSession s = SessionManager.session();
			Group g = (Group) s.getAttribute("group");
			s.invalidate();
			this.gr.delete(g);
			return "redirect:/group/list";
	
	}
	@ModelAttribute("availableGroups")
	public List<Group> getGroups(){
		return this.gr.findAll();
	}

}
