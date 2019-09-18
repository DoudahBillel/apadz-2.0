package com.projet.ressources.models.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CaracteristiqueF_ressource {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name="ressource_id")
	private Ressource ressource; 
	
	@ManyToOne
	@JoinColumn(name="caracteristiqueF_id")
	private CaracteristiqueF caracteristiqueF;
	
	private String valeur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public CaracteristiqueF getCaracteristiqueF() {
		return caracteristiqueF;
	}

	public void setCaracteristiqueF(CaracteristiqueF caracteristiqueF) {
		this.caracteristiqueF = caracteristiqueF;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	} 
	
	
	
	
	
	
}
