package com.projet.ressources.models.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String chemin;
	
	@OneToMany(mappedBy="document")
	private Set<Privilege_document> privilege_document; 
	
	@ManyToOne
	@JoinColumn(name="compte_id")
	private Compte compte ; 
	

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Set<Privilege_document> getPrivilege_document() {
		return privilege_document;
	}

	public void setPrivilege_document(Set<Privilege_document> privilege_document) {
		this.privilege_document = privilege_document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

}
