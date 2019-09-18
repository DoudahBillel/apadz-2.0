package com.projet.ressources.models.metier.presentation.repositories;

import com.projet.ressources.models.beans.articles.CategorieArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CategorieArticleRepository extends JpaRepository<CategorieArticle, Integer> {

}
