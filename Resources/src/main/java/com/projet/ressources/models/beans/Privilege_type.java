package com.projet.ressources.models.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * assoication entre groupe, type et privilege
 *i-e : le le groupe en question possède les privileges en question le type en question.
 *
 */
@Entity
public class Privilege_type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name="type_id")
	private Type type; 
	
	@ManyToOne
	@JoinColumn(name="groupe_id")
	private Groupe groupe ; 
	
	
	private String privilege; 
	
	@Column(name="date_ajout")
	private Date addedDate;
	
	public Privilege_type() {}
	
	public Privilege_type(Type type, Groupe groupe, String privilege) {
		this.type = type ; 
		this.groupe = groupe ; 
		this.privilege = privilege ; 
		this.addedDate = new java.sql.Date(System.currentTimeMillis()) ; 
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	

}
