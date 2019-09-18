package com.projet.ressources.controllers.permis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.Demande;
import com.projet.ressources.models.beans.Ressource;
import com.projet.ressources.models.metier.permis.DemandeRepository;
import com.projet.ressources.models.metier.ressources.RessourceRepository;


@Controller
@RequestMapping("/permis")
public class PermisController {
	
	@Autowired 
	private DemandeRepository demandeRepo;
	
	@Autowired
	private CompteRepository compteRepo;
	
	@Autowired
	private RessourceRepository ressourceRepo;
	
	
	
	@RequestMapping("/demande")
	public String home(Demande demande, Model model) {
		
		String message="";
		List<Ressource> list = ressourceRepo.findAll();
		model.addAttribute("message", message);
	    model.addAttribute("ressources", list);
		
		
		return "general/permis/demande"; 
			
	}
	
	@PostMapping("/demande")
	public String demnder(@Valid Demande demande, BindingResult bindingResult,
			              @RequestParam Map<String,String> allParams, Model model) {
		
		Boolean success = false;
		String message = "";
		
		if (bindingResult.hasErrors()) {
			
			message = "Veuillez corriger les erreurs et renvoyer le formulaire à nouveau !";			
		}else {	
					
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String today = format.format( new Date() );		
			demande.setDateDemande(today);					
					
			String typeOrg = demande.getTypeOriganisme();				
		    Boolean isOrganisme = !(typeOrg.equals("none"));
			demande.setIsOrganisme(isOrganisme); 
			
			// Statuts de la demande  pret-->(apres confirmation par mail et/ou sms) ecnours -->(Apres traitement par l'agent) validée / refusée
			demande.setStatutDemande("pret");
					
			String lastName = demande.getNom();
			String firstName = demande.getPrenom();
			String userName = lastName+"_"+firstName;
			String password = "password"; // a changer plutard !
			
			Long idRessource = Long.parseLong(allParams.get("designation"));
			Ressource ressource = ressourceRepo.findById(idRessource).orElse(null);
			if(ressource == null){
				// En cas ou l'utilisateur change l'html de la page (et ceci ne peut pas etre traite autrement!)
				message = "Erreur dans la selection de la ressource !" ;
				
			}else{
			demande.setRessource(ressource);
							
			Compte compte = new Compte(lastName, firstName, userName, password, 1);
			compteRepo.save(compte);		
			demande.setCompte(compte);
			
			// Ici l'envoie du couriel de confirmation
			
				
			this.demandeRepo.save(demande);
			success = true;
			message = "Votre demande est enregistrée avec succès,"
					+ " mais vous devriez la confirmer avec votre Email.";
			}
		
		}
		
		model.addAttribute("success",success);
		model.addAttribute("message", message);
		return "general/permis/demande"; 
		
			 
	}
	
	

}
