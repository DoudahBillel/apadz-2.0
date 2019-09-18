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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotEmpty
	private String designation;
	private String description;
	
	@Column(name="date_ajout")
	private Date addedDate; 

	@ManyToMany(mappedBy="groupes")
	private Set<Compte> comptes; 
	
	@OneToMany(mappedBy="groupe")
	private Set<Privilege_document> privilege_document; 
	
	@OneToMany(mappedBy="groupe")
	private Set<Privilege_type> privilege_type; 
	
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	public Set<Privilege_document> getPrivilege_document() {
		return privilege_document;
	}

	public Set<Privilege_type> getPrivilege_type() {
		return privilege_type;
	}

	public void setPrivilege_type(Set<Privilege_type> privilege_type) {
		this.privilege_type = privilege_type;
	}

	public void setPrivilege_document(Set<Privilege_document> privilege_document) {
		this.privilege_document = privilege_document;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
