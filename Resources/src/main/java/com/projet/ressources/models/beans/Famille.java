package com.projet.ressources.models.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Famille {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Designation;
	private String description;
	@Column(name="date_ajout")
	private Date addedDate;
	
	//id de la famille mere
	@ManyToOne
	@JoinColumn(name="famille_mere_id")
	private Famille familleMere;
	
	//Set des familles filles
	@OneToMany(mappedBy="familleMere")
	private Set<Famille> famillesFilles; 
	
	
	public Famille getFamilleMere() {
		return familleMere;
	}

	public void setFamilleMere(Famille familleMere) {
		this.familleMere = familleMere;
	}

	public Set<Famille> getFamillesFilles() {
		return famillesFilles;
	}

	public void setFamillesFilles(Set<Famille> famillesFilles) {
		this.famillesFilles = famillesFilles;
	}

	@ManyToMany
	@JoinTable(name = "caracteristiqueF_famille", 
	joinColumns = @JoinColumn(name = "famille_id"), 
	inverseJoinColumns = @JoinColumn(name = "caracteristiqueF_id"))
	private Set<CaracteristiqueF> caracteristiques; 

	
	@ManyToMany
	@JoinTable(name="famille_ressource",
	joinColumns=@JoinColumn(name="famille_id"),
	inverseJoinColumns=@JoinColumn(name="ressource_id"))
	private Set<Ressource> ressources; 
	
	
	public Set<CaracteristiqueF> getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(Set<CaracteristiqueF> caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	public Set<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
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
