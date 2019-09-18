package com.projet.ressources.models.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ressource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String designation; 
	private String description; 
	@Column(name="date_ajout")
	private Date addedDate; 
	private String valide; 
	@ManyToOne
	@JoinColumn(name="type_id")
	private Type type; 
	
	@OneToMany(mappedBy="ressource")
	private Set<Historique> historiques ; 
	
	public Set<Historique> getHistoriques() {
		return historiques;
	}
	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}
	// les valeurs de la ressource en question par rapport aux caractéristiques de la famille (non pas du type) 
	@OneToMany(mappedBy="ressource")
	private Set<CaracteristiqueF_ressource> caracteristiqueF_ressource; 
	
	
	// les valeurs de la ressource en question par rapport aux caractéristiques de la famille (non pas du type) 
	@OneToMany(mappedBy="ressource")
	private Set<Caracteristique_ressource> caracteristique_ressource;
	
	//Les ctas associés à la ressource en question 
	@ManyToMany(mappedBy="ressources")
	private Set<CTA> ctas;
	
	//lien vers l'association exploiter là où on aura les id des comptes qui exploitent la ressource en question
	@OneToMany(mappedBy="ressource")
	private Set<Exploiter> exploiter ; 
	
	//les familles aux quelles la ressource en question appartient
	@ManyToMany(mappedBy="ressources")
	private Set<Famille> familles; 
	
	
	//les projet qui exploitent la ressource en question 
	@ManyToMany(mappedBy="ressources")
	private Set<Projet> projets;
	
	
	//les demandes concernant cette ressource
	@OneToMany(mappedBy = "ressource")
	private Set<Demande> demandes;
	
	
	
	public Set<Projet> getProjets() {
		return projets;
	}
	public void setProjets(Set<Projet> projets) {
		this.projets = projets;
	}
	public Set<Famille> getFamilles() {
		return familles;
	}
	public void setFamilles(Set<Famille> familles) {
		this.familles = familles;
	}
	public Set<Exploiter> getExploiter() {
		return exploiter;
	}
	public void setExploiter(Set<Exploiter> exploiter) {
		this.exploiter = exploiter;
	}
	public Set<CTA> getCtas() {
		return ctas;
	}
	public void setCtas(Set<CTA> ctas) {
		this.ctas = ctas;
	}
	public Set<Caracteristique_ressource> getCaracteristique_ressource() {
		return caracteristique_ressource;
	}
	public void setCaracteristique_ressource(Set<Caracteristique_ressource> caracteristique_ressource) {
		this.caracteristique_ressource = caracteristique_ressource;
	}
	public Set<CaracteristiqueF_ressource> getCaracteristiqueF_ressource() {
		return caracteristiqueF_ressource;
	}
	public void setCaracteristiqueF_ressource(Set<CaracteristiqueF_ressource> caracteristiqueF_ressource) {
		this.caracteristiqueF_ressource = caracteristiqueF_ressource;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
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
	public String getValide() {
		return valide;
	}
	public void setValide(String valide) {
		this.valide = valide;
	}
	
	public Long getId() {
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	} 
	
	
	
	
	
}
