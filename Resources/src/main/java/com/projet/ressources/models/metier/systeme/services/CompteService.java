package com.projet.ressources.models.metier.systeme.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.Groupe;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import com.projet.ressources.models.metier.systeme.repositories.GroupRepository;

@Service
public class CompteService {
	
	@Autowired 
	private CompteRepository compteRepository ; 
	
	@Autowired 
	private GroupRepository groupRepository ; 

	
	@Autowired 
	private PasswordEncoder passwordEncoder ; 
	
	
	/**
	 * 
	 * @param id
	 * @return if account having primary key = id exist or not
	 */
	public boolean existsById(Long id) {
		return this.compteRepository.existsById(id) ; 
	}	
	
	/**
	 * 
	 * @param id
	 * @return account having primary key = id
	 */
	public Compte findCompteById(Long id) {
		return this.compteRepository.getOne(id) ; 
	}
	
	
	
	/**
	 * 
	 * @param compte : the account to be saved 
	 * @param groupIds : the groups which account belongs to. 
	 */
	public void createCompte(Compte compte, String groupIds) {
		
		if(groupIds != null && groupIds != "") {
			String[] ids = groupIds.split("#") ;
			List<Long> idsLong = new ArrayList<Long>() ;  
			for(int i=0;i<ids.length; i++) {
				idsLong.add(i,Long.parseLong(ids[i]));
			}

			List<Groupe> groupes =  this.groupRepository.findAllById(idsLong) ;			
			compte.setGroupes(new HashSet<Groupe>(groupes));
			
			for(Groupe group : groupes) {
				Set<Compte> accounts = group.getComptes() ;
				accounts.add(compte); 
				this.groupRepository.save(group) ; 
			}
			
		}
		
		// à revoir après
		if(compte.getPassword() != "")
			compte.setPassword(passwordEncoder.encode(compte.getPassword()));
		
		this.compteRepository.save(compte) ; 	
	}
	
	
	/**
	 * 
	 * @param id
	 * @return true if account has been deleted successfully, false otherwise
	 */
	public boolean deleteCompte(Long id) {
		if(this.compteRepository.existsById(id)) {
			this.compteRepository.deleteById(id); 
			return true; 
		}
		else
			return false; 
	}
	
	/**
	 * 
	 * @param typeCompte
	 * @return 
	 */
	public long countByTypeCompte(int typeCompte) {
		return  this.compteRepository.countByTypeCompte(typeCompte);  
	}
	
	/**
	 * 
	 * @param typeCompte
	 * @return accounts having field typeCompte = typeCompte
	 */
	public List<Compte> findByTypeCompte(int typeCompte){
		return this.compteRepository.findByTypeCompte(typeCompte) ; 
	}
	
	/**
	 * 
	 * @return accounts ordered by Id 
	 */
	public List<Compte> findAllByOrderById(){
		return this.compteRepository.findAllByOrderById(); 
	}
	
	public Compte findLastInserted() {
		return this.compteRepository.findTopByOrderByIdDesc() ; 
	}
	
	public Compte findByUserName(String userName) {
		return this.compteRepository.findByUserName(userName) ; 
	}

}
