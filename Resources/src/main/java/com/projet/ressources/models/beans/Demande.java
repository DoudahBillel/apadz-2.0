package com.projet.ressources.models.beans;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Demande {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
/** Identification du demandeur **/
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	private String nom;

	@Size(min=1, message="Ce champ ne peut pas être vide!")
	private String prenom;

	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String pays;

	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String nationalite;

	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String pieceIdentite;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="\\d+", message="Ce champ ne peut contenir que des chiffres !")
	private String numPiece;
	
	private String dateDelivrence;
	
	private String regionDelivrence;
	
	private String valideDu;
	
	private String valideJusquau;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	private String adresse;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="\\d+", message="Ce champ ne peut contenir que des chiffres !")
	private String codePostal;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Email(message="Cette adresse est invalide!")
	private String email;

	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String codePays;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="\\d+", message="Ce champ ne peut contenir que des chiffres !")
	private String numTelephone;
	
	private Boolean isOrganisme;
		
	private String typeOriganisme;
	
	private String secteur;
	
	private String denomination;
	
	private String siegeSocial;
	
	@Pattern(regexp="\\d*", message="Ce champ ne peut contenir que des chiffres !")
	private String numRgistreCommerce;
	
	private String typeLabo;
	
	private String uniteDeRecherche;
	
	private String detailsDemande;
	
	// Le compte demandant
		@ManyToOne
		@JoinColumn(name="compte_id")
		private Compte compte;
	
/** Identification de la ressource **/
	
	// La ressource demandee
	@ManyToOne
	@JoinColumn(name="ressource_id")
	private Ressource ressource; 
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	private String materiel;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="\\d+", message="Ce champ ne peut contenir que des chiffres !")
	private String quantite;
	
	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String unite;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$",
	message="Date invalide!")
	private String debutCollecte;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	@Pattern(regexp="^\\d{4}[\\-\\/\\s]?((((0[13578])|(1[02]))[\\-\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-\\/\\s]?(([0-2][0-9])|(30)))|(02[\\-\\/\\s]?[0-2][0-9]))$",
	message="Date invalide!")
	private String finCollecte;
	
	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String modeAcquisition;
	
	@Pattern(regexp="^((?!none).)*$", message="Ce champ ne peut pas être vide!")
	private String finalite;
	
	@Size(min=1, message="Ce champ ne peut pas être vide!")
	private String modalite;
	
	private String detailsRessource;
	
	private String descriptionCTA;
	
	private String typeCTA;
	
	private String localiteCTA;
	
	private String statutCTA;
	
	private String modeAccesCTA;
		
	private String statutDemande;
	
	private String dateDemande;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getPieceIdentite() {
		return pieceIdentite;
	}

	public void setPieceIdentite(String pieceIdentite) {
		this.pieceIdentite = pieceIdentite;
	}

	public String getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(String numPiece) {
		this.numPiece = numPiece;
	}

	public String getDateDelivrence() {
		return dateDelivrence;
	}

	public void setDateDelivrence(String dateDelivrence) {
		this.dateDelivrence = dateDelivrence;
	}

	public String getRegionDelivrence() {
		return regionDelivrence;
	}

	public void setRegionDelivrence(String regionDelivrence) {
		this.regionDelivrence = regionDelivrence;
	}

	public String getValideDu() {
		return valideDu;
	}

	public void setValideDu(String valideDu) {
		this.valideDu = valideDu;
	}

	public String getValideJusquau() {
		return valideJusquau;
	}

	public void setValideJusquau(String valideJusquau) {
		this.valideJusquau = valideJusquau;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}

	public Boolean getIsOrganisme() {
		return isOrganisme;
	}

	public void setIsOrganisme(Boolean isOrganisme) {
		this.isOrganisme = isOrganisme;
	}

	public String getTypeOriganisme() {
		return typeOriganisme;
	}

	public void setTypeOriganisme(String typeOriganisme) {
		this.typeOriganisme = typeOriganisme;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getSiegeSocial() {
		return siegeSocial;
	}

	public void setSiegeSocial(String siegeSocial) {
		this.siegeSocial = siegeSocial;
	}

	public String getNumRgistreCommerce() {
		return numRgistreCommerce;
	}

	public void setNumRgistreCommerce(String numRgistreCommerce) {
		this.numRgistreCommerce = numRgistreCommerce;
	}

	public String getTypeLabo() {
		return typeLabo;
	}

	public void setTypeLabo(String typeLabo) {
		this.typeLabo = typeLabo;
	}

	public String getUniteDeRecherche() {
		return uniteDeRecherche;
	}

	public void setUniteDeRecherche(String uniteDeRecherche) {
		this.uniteDeRecherche = uniteDeRecherche;
	}

	public String getDetailsDemande() {
		return detailsDemande;
	}

	public void setDetailsDemande(String detailsDemande) {
		this.detailsDemande = detailsDemande;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getDebutCollecte() {
		return debutCollecte;
	}

	public void setDebutCollecte(String debutCollecte) {
		this.debutCollecte = debutCollecte;
	}

	public String getFinCollecte() {
		return finCollecte;
	}

	public void setFinCollecte(String finCollecte) {
		this.finCollecte = finCollecte;
	}

	public String getModeAcquisition() {
		return modeAcquisition;
	}

	public void setModeAcquisition(String modeAcquisition) {
		this.modeAcquisition = modeAcquisition;
	}

	public String getFinalite() {
		return finalite;
	}

	public void setFinalite(String finalite) {
		this.finalite = finalite;
	}

	public String getModalite() {
		return modalite;
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

	public String getDetailsRessource() {
		return detailsRessource;
	}

	public void setDetailsRessource(String detailsRessource) {
		this.detailsRessource = detailsRessource;
	}

	public String getDescriptionCTA() {
		return descriptionCTA;
	}

	public void setDescriptionCTA(String descriptionCTA) {
		this.descriptionCTA = descriptionCTA;
	}

	public String getTypeCTA() {
		return typeCTA;
	}

	public void setTypeCTA(String typeCTA) {
		this.typeCTA = typeCTA;
	}

	public String getLocaliteCTA() {
		return localiteCTA;
	}

	public void setLocaliteCTA(String localiteCTA) {
		this.localiteCTA = localiteCTA;
	}

	public String getStatutCTA() {
		return statutCTA;
	}

	public void setStatutCTA(String statutCTA) {
		this.statutCTA = statutCTA;
	}

	public String getModeAccesCTA() {
		return modeAccesCTA;
	}

	public void setModeAccesCTA(String modeAccesCTA) {
		this.modeAccesCTA = modeAccesCTA;
	}

	public String getStatutDemande() {
		return statutDemande;
	}

	public void setStatutDemande(String statutDemande) {
		this.statutDemande = statutDemande;
	}

	public String getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}

	
}
