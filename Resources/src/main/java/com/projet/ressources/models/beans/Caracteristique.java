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
import javax.persistence.OneToMany;

@Entity
public class Caracteristique {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String designation;
	private String description;
	@Column(name="date_ajout")
	private Date addedDate;

	@OneToMany(mappedBy="caracteristique")
	private Set<Caracteristique_ressource> caracteristique_ressource;
	
	@ManyToMany
	@JoinTable(name="caracteristique_type",
	joinColumns=@JoinColumn(name="caracteristique_id"),
	inverseJoinColumns=@JoinColumn(name="type_id"))
	private Set<Type> types ; 
	
	
	
	public Set<Caracteristique_ressource> getCaracteristique_ressource() {
		return caracteristique_ressource;
	}

	public void setCaracteristique_ressource(Set<Caracteristique_ressource> caracteristique_ressource) {
		this.caracteristique_ressource = caracteristique_ressource;
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

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
