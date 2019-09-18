package com.projet.ressources.models.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projet.ressources.models.beans.articles.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Compte implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(name="nom")
	@NotNull
	@NotEmpty
	private String lastName;

	@Column(name="prenom")
	@NotNull
	@NotEmpty
	private String firstName;
	
	@Column(name="email")
	@NotEmpty
	@NotNull
	private String userName;
	@NotEmpty
	@NotNull
	@Size(min=5)
	private String password;
	
	@ManyToMany
	@JoinTable(name="compte_groupe",
	joinColumns=@JoinColumn(name="compte_id"),
	inverseJoinColumns=@JoinColumn(name="groupe_id"))
	private Set<Groupe> groupes;
	
	//lien vers l'association exploiter
	//pour avoir les id des ressources exploités par le compte en question
	@OneToMany(mappedBy="compte")
	private Set<Exploiter> exploiter;
	
	
	/**
	 * switch(typeCompte) : 
	 * case 4: return "Admin général"
	 * case 3: return "admin métier"
	 * case 2: return "expert ressources"
	 * case 1: return "Simple user"
	 */
	private int typeCompte ;

	@JsonIgnore
	@OneToMany(mappedBy="compte")
	private Set<Article> articles ;
	
	@OneToMany(mappedBy="compte")
	private Set<Document> documents ; 
	
	@OneToMany(mappedBy = "compte")
	private Set<Demande> demandes;

	public Compte() {
		super();
	}

	public Compte(@NotNull String lastName, @NotNull String firstName, @NotNull String userName, String password,
			int typeCompte) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	public String getUserName() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void removeGroupe(Groupe groupeDelete) {
		Set<Groupe> groupes = this.groupes ; 
		for(Groupe groupe : groupes) {
			if(groupe.getId() == id) {
				this.groupes.remove(groupeDelete) ; 
			}
		}
		this.setGroupes(groupes);
	}

	public boolean canAddRessource(long typeId) {
		for(Groupe groupe : this.groupes) {
			System.out.println("---------------------------------------- "+typeId ) ; 
			Set<Privilege_type> prvlgs = groupe.getPrivilege_type(); 
			System.out.println("this group "+groupe.getDesignation()) ;
			for(Privilege_type prvlg : prvlgs ) {
				String str = prvlg.getPrivilege() ;
				System.out.println("Has these privileges "+str+" at this ressource "+prvlg.getType().getId()+" charat(3) =="+str.charAt(3)) ; 
				if(str.charAt(0) == '1') {
					if(prvlg.getType().getId() == typeId)
						return true; 
				}
			}
		}

		return false ; 
	}
	
	public boolean canUpdateRessource(long typeId) {
		for(Groupe groupe : this.groupes) {
			Set<Privilege_type> prvlgs = groupe.getPrivilege_type(); 
			for(Privilege_type prvlg : prvlgs ) {
				String str = prvlg.getPrivilege() ; 
				if(str.charAt(1) == '1') {
					return true; 
				}
			}
		}
		return false ; 
	}
	
	public boolean canValidateRessource(long typeId) {
		for(Groupe groupe : this.groupes) {
			Set<Privilege_type> prvlgs = groupe.getPrivilege_type(); 
			for(Privilege_type prvlg : prvlgs ) {
				String str = prvlg.getPrivilege() ; 
				if(str.charAt(2) == '1') {
					return true; 
				}
			}
		}
		return false ; 
	}
	
	public boolean canDeleteRessource(long typeId) {
		for(Groupe groupe : this.groupes) {
			Set<Privilege_type> prvlgs = groupe.getPrivilege_type(); 
			for(Privilege_type prvlg : prvlgs ) {
				String str = prvlg.getPrivilege() ; 
				if(str.charAt(3) == '1') {
					return true; 
				}
			}
		}
		return false ; 
	}
}
