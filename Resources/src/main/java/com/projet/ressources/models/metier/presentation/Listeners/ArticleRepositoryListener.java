package com.projet.ressources.models.metier.presentation.Listeners;

import com.projet.ressources.controllers.presentation.RestPresentationController;
import com.projet.ressources.models.beans.articles.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Component
@RepositoryEventHandler
public class ArticleRepositoryListener {

    @Autowired
    private RestPresentationController restPresentationController;

    @HandleBeforeCreate
    public void onBeforePersistArticle(Article article) {
        article.setAddedDate(LocalDateTime.now());
        article.setCompte(this.restPresentationController.fetchLoggedInUserDetails());
    }
}
