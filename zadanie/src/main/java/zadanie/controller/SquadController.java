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
import zadanie.entity.Squad;
import zadanie.repository.SquadRepository;

@Controller
@RequestMapping("/group")
public class SquadController {
	@Autowired
	private SquadRepository gr;

	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("squad", new Squad());
		return "/group/addGroup";
	}

	@PostMapping("/add")
	public String addPost(@Valid @ModelAttribute Squad squad, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "/group/addGroup";
		}

		List<Squad> squads = this.gr.findAll();
		for (Squad g : squads) {
			if (g.getName().equals(squad.getName())) {
				m.addAttribute("msg", "This group name address is already used. Try different name.");
				return "/group/addGroup";
			}
		}
		this.gr.save(squad);
//		HttpSession s = SessionManager.session();
//		s.setAttribute("squad", squad);
		return "redirect:/group/list";
	}

	@GetMapping("/{id}/edit")
	@Transactional
	public String changeSquad(Model m, @PathVariable Long id) {
//		HttpSession s = SessionManager.session();
		Squad g =  this.gr.findById(id);
		m.addAttribute("squad", g);
		return "/group/groupUpdate";
	}

	@PostMapping(value="{id}/edit")
	public String changePost(@Valid @ModelAttribute Squad squad, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/group/list";
		}
//		HttpSession s = SessionManager.session();
//		Squad g = (Squad) s.getAttribute("squad");
//		squad.setId(g.getId());
		this.gr.save(squad);
		return "redirect:/group/list";
	}

	@GetMapping("/{id}/del")
	public String deletePost(@PathVariable long id) {
//			HttpSession s = SessionManager.session();
//			Squad g = (Squad) s.getAttribute("squad");
//			s.invalidate();
			this.gr.delete(id);
			return "redirect:/group/list";
	
	}
	@ModelAttribute("availableSquads")
	public List<Squad> getSquads(){
		return this.gr.findAll();
	}

}
