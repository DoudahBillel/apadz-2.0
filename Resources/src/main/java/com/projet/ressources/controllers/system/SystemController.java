package com.projet.ressources.controllers.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import com.projet.ressources.models.metier.systeme.repositories.GroupRepository;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private CompteRepository compteRepository ; 
	@Autowired 
	private GroupRepository groupRepository; 
	
	@RequestMapping("")
	public String home(Model model) {
		
		Long userNbr = this.compteRepository.count();
		Long groupNbr = this.groupRepository.count(); 
		
		model.addAttribute("comptes",userNbr) ; 
		model.addAttribute("groupes",groupNbr) ; 
		
		return "general/systeme/dashboard";
	}

	@RequestMapping("/login")
	public String login() {
		return "general/systeme/login"; 
	}
	
	
	
	
	
	
}
