package com.projet.ressources.models.metier.systeme.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.Groupe;
import com.projet.ressources.models.beans.Privilege_type;
import com.projet.ressources.models.beans.Type;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import com.projet.ressources.models.metier.systeme.repositories.GroupRepository;
import com.projet.ressources.models.metier.systeme.repositories.PrivilegeTypeRepository;
import com.projet.ressources.models.metier.systeme.repositories.TypeRepository;

@Service
public class GroupService {

	@Autowired 
	private CompteRepository compteRepository ; 
	
	@Autowired 
	private GroupRepository groupRepository ; 
	
	@Autowired
	private TypeRepository typeRepository ; 
	
	@Autowired 
	private PrivilegeTypeRepository privilegeTypeRepository ; 
	

	/**
	 * 
	 * @return all groups
	 */
	public List<Groupe> findAll(){
		return this.groupRepository.findAll() ; 
	}
	
	/**
	 * 
	 * @return groups ordered by Id
	 */
	public List<Groupe> findAllByOrderById(){
		return this.groupRepository.findAllByOrderById(); 
	}
	
	/**
	 * 
	 * @param id
	 * @return group having primary key = id
	 */
	public Groupe findById(long id) {
		return this.groupRepository.getOne(id) ; 
	}
	
	/**
	 * 
	 * @param id
	 * @return if group with primary key = id exists or not
	 */
	public boolean existsById(long id) {
		return this.groupRepository.existsById(id) ; 
	}
	
	/**
	 * 
	 * @return last group inserted
	 */
	public Groupe findLastInserted() {
		return this.groupRepository.findTopByOrderByIdDesc(); 
	}
	
	
	/**
	 * 
	 * @param groupe
	 * @param userId
	 * Create new group
	 */
	public void save(Groupe groupe, String userId) {
		
		groupe.setAddedDate(new java.sql.Date(System.currentTimeMillis()));
		
		if( userId != null && userId!= "") {
			String[] ids = userId.split("#") ;
			List<Long> idsLong = new ArrayList<Long>() ;  
			for(int i=0;i<ids.length; i++) {
				idsLong.add(i,Long.parseLong(ids[i]));
			}
			
			List<Compte> comptes =  this.compteRepository.findAllById(idsLong) ;			
			groupe.setComptes(new HashSet<Compte>(comptes));
			this.groupRepository.save(groupe); 	
			//Ajouté le groupe en question dans la liste des groupes de chaque compte 
			for(Compte cpt : comptes) {
				Set<Groupe> groupes = cpt.getGroupes(); 
				groupes.add(groupe) ; 
				cpt.setGroupes(groupes);
				this.compteRepository.save(cpt) ; 
			}
		}
		else {
						
			this.groupRepository.save(groupe); 
		}
		
	}
	
	/**
	 * 
	 * @param groupe
	 * @param userId
	 * Update group
	 */
	public void update(Groupe groupe, String userId) {
		
		Groupe group = this.groupRepository.getOne(groupe.getId()) ; 
		Set<Compte> oldAccounts = group.getComptes() ; 
		for(Compte account : oldAccounts) {
			Set<Groupe> accountGroupes = account.getGroupes() ; 
			accountGroupes.remove(group);
			account.setGroupes(accountGroupes);
			this.compteRepository.save(account) ;
		}
		group.setComptes(new HashSet<Compte>()) ;
		this.groupRepository.save(group) ; 
		
		
		if( userId != null && userId!= "") {
			String[] ids = userId.split("#") ;
			List<Long> idsLong = new ArrayList<Long>() ;  
			for(int i=0;i<ids.length; i++) {
				idsLong.add(i,Long.parseLong(ids[i]));		
			}
			
			List<Compte> comptes =  this.compteRepository.findAllById(idsLong) ;
			
			groupe.setComptes(new HashSet<Compte>(comptes));
			this.groupRepository.save(groupe);
			
			for(Compte cpt : comptes) {
				Set<Groupe> myGroupes = cpt.getGroupes(); 
				myGroupes.add(groupe) ;
				cpt.setGroupes(myGroupes);
				this.compteRepository.save(cpt) ; 
			}
			
		}
	}
	
	
	public boolean delete(long id) {
		
		if(this.groupRepository.existsById(id)) {
			//set accounts 
			Groupe groupe = this.groupRepository.findById(id).get();
			Set<Compte> accounts = groupe.getComptes() ; 
			for(Compte compte : accounts) {
				Set<Groupe> groupes = compte.getGroupes();
				groupes.remove(groupe); 
				compte.setGroupes(groupes);
				this.compteRepository.save(compte) ; 
			}
			groupe.setComptes(new HashSet<Compte>());
			//set privileges 
			Set<Privilege_type> prvlgs = groupe.getPrivilege_type() ;
			groupe.setPrivilege_type(new HashSet<Privilege_type>());
			
			for(Privilege_type prvlg : prvlgs) {
				this.privilegeTypeRepository.delete(prvlg);
			}
			
			this.groupRepository.delete(groupe);
			 
			return true; 
		}
		return false; 
	}


	public void setPrivilege(long id, String  ressources) {
		
		
		Groupe groupe = this.groupRepository.findById(id).get();
		Set<Privilege_type> exestingPrvlgs = groupe.getPrivilege_type(); 
		for(Privilege_type prvlg : exestingPrvlgs) {
			this.privilegeTypeRepository.delete(prvlg);
		}
		groupe.setPrivilege_type(new HashSet<Privilege_type>());
		this.groupRepository.save(groupe) ; 
		
		
		if(ressources != null && ressources != "") {
			String[] ressourceByP = ressources.split("@") ; 
			Set<Privilege_type> privileges_types = new HashSet<Privilege_type>() ; 
			
			for(String str : ressourceByP) {
				String[] oneRessource = str.split("#") ; 
				Long typeId = Long.parseLong(oneRessource[0]) ; 
				String privilege = oneRessource[1] ;
				
				Type type = this.typeRepository.findById(typeId).get() ;
				
				Privilege_type privilege_type = new Privilege_type(type, groupe, privilege) ; 
				privilegeTypeRepository.save(privilege_type) ; 
				
				privileges_types = type.getPrivilege_type(); 
				privileges_types.add(privilege_type) ; 
				this.typeRepository.save(type) ; 
				
				
				privileges_types = groupe.getPrivilege_type() ; 
				privileges_types.add(privilege_type); 
				this.groupRepository.save(groupe) ; 
			}
		}
		
	}
	
	public void save(Groupe groupe) {
		this.groupRepository.save(groupe) ;
	}
	
}
