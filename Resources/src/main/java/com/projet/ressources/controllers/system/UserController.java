package com.projet.ressources.controllers.system;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.Groupe;
import com.projet.ressources.models.metier.systeme.services.CompteService;
import com.projet.ressources.models.metier.systeme.services.GroupService;

@Controller
@RequestMapping("system/user")
public class UserController {
	 
	
	@Autowired 
	private CompteService compteService ;
	
	@Autowired 
	private GroupService groupService ; 
	
	
	@GetMapping("")
	public String getUsers(Model model){
		
		long nbrAdminSystem = this.compteService.countByTypeCompte(4) ;
		long nbrAdminRessource = this.compteService.countByTypeCompte(3) ;
		long nbrExpertRessource = this.compteService.countByTypeCompte(2) ;
		long nbrSimpleUser = this.compteService.countByTypeCompte(1) ;
		
		model.addAttribute("adminSystem", nbrAdminSystem);
		model.addAttribute("adminRessource", nbrAdminRessource);
		model.addAttribute("expertRessource", nbrExpertRessource);
		model.addAttribute("simpleUser", nbrSimpleUser);
		//model.addAttribute("users",this.compteService.findAll()); 
		model.addAttribute("users",this.compteService.findAllByOrderById()); 
		
		return "general/systeme/user/listUsers" ; 
	}
	
	@GetMapping("/{id}")
	public String getUserById(@PathVariable("id") long id, Model model) {
		if(this.compteService.existsById(id)) {
			Compte compte = this.compteService.findCompteById(id);
			Set<Groupe> groupes = compte.getGroupes() ; 
			
			model.addAttribute("compte",compte) ; 
			model.addAttribute("groupes",groupes) ;
			System.out.println(this.compteService.findByUserName(compte.getUserName()).getPassword()) ; 
			
			return "general/systeme/user/detailCompte"; 
		}
		else
			return "redirect:/system/user"; 
	}
	
	
	@RequestMapping("/new")
	public String newUser(Model model) {
		Compte compte = new Compte() ; 
		
		List<Groupe> groupes = this.groupService.findAll() ; 
		
		model.addAttribute("compte",compte) ; 
		model.addAttribute("groupes",groupes) ; 
		
		return "general/systeme/user/createUser"; 
	}
	
	@PostMapping("/new")
	public String createUser(@ModelAttribute @Valid Compte compte,BindingResult bindingResult ,@RequestParam(value="groupIds",required=false) String groupIds, Model model) {
		
		if(bindingResult.hasErrors()) {
			List<Groupe> groupes = this.groupService.findAll() ;
			model.addAttribute("groupes",groupes) ; 
			return "general/systeme/user/createUser"; 
		}
		
		this.compteService.createCompte(compte, groupIds); 
		
		long id = compte.getId() != null ? compte.getId() : this.compteService.findLastInserted().getId(); 

		return "redirect:/system/user/"+id; 
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		
		if(this.compteService.deleteCompte(id)) {
			return "redirect:/system/user"; 
		}
		
		return "redirect:/system/user?deleteError" ;
	}
	
	@GetMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
		
		if(this.compteService.existsById(id)) {
			Compte user = this.compteService.findCompteById(id) ;
			Set<Groupe> myGroups = user.getGroupes(); 
			List<Groupe> groups =this.groupService.findAllByOrderById() ; 
			
			model.addAttribute("compte",user) ; 
			model.addAttribute("myGroups",myGroups) ;
			model.addAttribute("groupes", groups) ; 
			
			return "general/systeme/user/createUser";
		}
		else 
			return "redirect:/system/user"; 
		 
	}
	
	@GetMapping("/type/{type}")
	public String findByType(@PathVariable("type") long type ,Model model) {
		if(type>=1 && type<=4) {
			
			long nbrAdminSystem = this.compteService.countByTypeCompte(4) ;
			long nbrAdminRessource = this.compteService.countByTypeCompte(3) ;
			long nbrExpertRessource = this.compteService.countByTypeCompte(2) ;
			long nbrSimpleUser = this.compteService.countByTypeCompte(1) ;
			
			model.addAttribute("adminSystem", nbrAdminSystem);
			model.addAttribute("adminRessource", nbrAdminRessource);
			model.addAttribute("expertRessource", nbrExpertRessource);
			model.addAttribute("simpleUser", nbrSimpleUser);
			
			model.addAttribute("users",this.compteService.findByTypeCompte((int)type));
			return "general/systeme/user/listUsers" ; 
		}
		else
			return "redirect:/system/user"; 
		
	}
	
	
	

}
