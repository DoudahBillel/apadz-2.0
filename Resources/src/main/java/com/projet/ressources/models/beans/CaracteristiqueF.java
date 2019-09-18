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
public class CaracteristiqueF {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String designation;
	private String description;
	
	@Column(name="date_ajout")
	private Date addedDate;
	
	@ManyToMany(mappedBy="caracteristiques")
	private Set<Famille> familles; 
	
	@OneToMany(mappedBy="caracteristiqueF")
	private Set<CaracteristiqueF_ressource> caracteristiqueF_ressource;
	
	
	
	
	
	public Set<Famille> getFamilles() {
		return familles;
	}

	public void setFamilles(Set<Famille> familles) {
		this.familles = familles;
	}

	public Set<CaracteristiqueF_ressource> getCaracteristiqueF_ressource() {
		return caracteristiqueF_ressource;
	}

	public void setCaracteristiqueF_ressource(Set<CaracteristiqueF_ressource> caracteristiqueF_ressource) {
		this.caracteristiqueF_ressource = caracteristiqueF_ressource;
	}

	public String getDesignation() {
		return designation;
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



	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
