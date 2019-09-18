package com.projet.ressources.models.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String designation;
	private String description;
	@Column(name="date_ajout")
	private Date addedDate;
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@OneToMany(mappedBy = "type")
	private Set<Ressource> ressources;
	
	@ManyToMany(mappedBy="types")
	private Set<Caracteristique> caracteristiques ; 
	
	@OneToMany(mappedBy="type")
	private Set<Privilege_type> privilege_type; 
	

	public String getDesignation() {
		return designation;
	}

	public Set<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}

	public Set<Caracteristique> getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(Set<Caracteristique> caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	public Set<Privilege_type> getPrivilege_type() {
		return privilege_type;
	}

	public void setPrivilege_type(Set<Privilege_type> privilege_type) {
		this.privilege_type = privilege_type;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
