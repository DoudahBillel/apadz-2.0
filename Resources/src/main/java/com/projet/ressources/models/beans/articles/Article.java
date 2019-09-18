package com.projet.ressources.models.beans.articles;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.File;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Lazy;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="titre")
	private String title;

	@Lob
	@Column(name="contenu")
	private String content;

	@Column(name="date_ajout")
	@JsonFormat(pattern = "dd MMM yyyy HH:mm:ss")
	private LocalDateTime addedDate;

	private String description;

	@ManyToOne
	private CategorieArticle categorieArticle;

	public String getNomCategorieArticle(){
		return this.categorieArticle.getDesignation();
	}

	public Integer getIdCategorieArticle() {
		return this.categorieArticle.getId();
	}

	@Lob
	@Lazy
	@JsonIgnore
	private byte[] image;

	@ManyToMany
	@JoinTable(name = "article_fichier", 
		joinColumns = @JoinColumn(name = "article_id"), 
		inverseJoinColumns = @JoinColumn(name = "fichier_id"))
	@JsonIgnore
	private Set<File> files;
	
	@ManyToOne
	@JsonIgnore
	private Compte compte;
}
