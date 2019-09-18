package com.projet.ressources.controllers.system;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.projet.ressources.models.metier.systeme.services.TypeService;

@Controller
@RequestMapping("system/group")
public class GroupController {
	
	@Autowired 
	private TypeService typeService ;
	
	@Autowired 
	private GroupService groupService; 
	
	@Autowired 
	private CompteService compteService; 
	
	
	@GetMapping("")
	public String getAllGroups(Model model) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Compte user = this.compteService.findByUserName(currentPrincipalName) ; 
		model.addAttribute("user",user);
		
		model.addAttribute("groupes",this.groupService.findAllByOrderById()) ;
		
		return "general/systeme/group/listGroupes" ; 
	}
	
	@GetMapping("/{id}")
	public String getGroupById(@PathVariable("id") long id, Model model) {
		if(this.groupService.existsById(id)) {
			
			
			Groupe  groupe = this.groupService.findById(id) ; 
			model.addAttribute("groupe", groupe);  
			
			return "general/systeme/group/detailGroup" ; 
		}
		else 
			return "redirect:/system/group"; 
	}
	
	
	
	@RequestMapping("/new")
	public String newGroupe(Groupe groupe, Model model) {
		List<Compte> users = this.compteService.findAllByOrderById();
		
		model.addAttribute("users",users) ; 
		model.addAttribute("ressources",this.typeService.findAll()) ; 
		
		return "general/systeme/group/createGroupe"; 
	}
	
	@PostMapping("/new")
	public String createGroupe(@ModelAttribute @Valid Groupe groupe, BindingResult bindingResult ,@RequestParam(value="userIds", required=false) String userId, Model model) {
 
		if (bindingResult.hasErrors()) {
			model.addAttribute("users",this.compteService.findAllByOrderById()) ; 
            return "general/systeme/group/createGroupe";
        }
		
		this.groupService.save(groupe, userId) ; 		
		long id = groupe.getId()!= null ? groupe.getId() :  this.groupService.findLastInserted().getId() ;

		
		return "redirect:/system/group/"+id; 
	}
	
	
	@GetMapping("/update/{id}")
	public String updateGroup(@PathVariable("id") long id,Model model) {
		if(this.groupService.existsById(id)) {
			
			Groupe groupe = this.groupService.findById(id); 
			model.addAttribute("groupe", groupe) ; 
			model.addAttribute("myUsers",groupe.getComptes()) ;
			model.addAttribute("users",this.compteService.findAllByOrderById());
			
			return "general/systeme/group/updateGroup" ; 
		}
		return "redirect:/system/group" ; 
	}
	
	
	@PostMapping("/update")
	public String setGroup(@ModelAttribute @Valid Groupe groupe ,BindingResult bindingResult,@RequestParam(value="userIds", required=false) String userId, Model model){
		if (bindingResult.hasErrors()) {
			Groupe thisGroupe = this.groupService.findById(groupe.getId()); 
			model.addAttribute("users",this.compteService.findAllByOrderById()) ; 
			model.addAttribute("myUsers",thisGroupe.getComptes()) ;
			
            return "general/systeme/group/updateGroup";
        }
		
		this.groupService.update(groupe, userId) ; 
		
		Long id = groupe.getId(); 

		return "redirect:/system/group/"+id; 
	}
	
	@GetMapping("/delete/{id}")
	public String deleteGroup(@PathVariable("id") long id) {
		
		if(this.groupService.delete(id)) {
			return "redirect:/system/group" ;
		}
		
		return "redirect:/system/group"; 
	}
	
	
	@GetMapping("/{id}/privileges/update")
	public String setPrivilege(@PathVariable("id") Long id, Model model) {
		
		if(this.groupService.existsById(id)) {

			Groupe groupe = this.groupService.findById(id) ; 
			model.addAttribute("groupe",groupe) ; 
			model.addAttribute("ressources",this.typeService.findAll()) ; 
			model.addAttribute("existingRessources", groupe.getPrivilege_type()) ;  
			
			return "general/systeme/group/setprivileges"; 
		}
		
		return "redirect:/system/group"; 
	}
	
	
	@PostMapping("/{id}/privileges")
	public String createPrivilege(@PathVariable("id") Long id, @RequestParam(value="ressources",required=false) String ressources) {
		
		this.groupService.setPrivilege(id, ressources) ; 
		
		return "redirect:/system/group/"+id ; 
	}
	
}
