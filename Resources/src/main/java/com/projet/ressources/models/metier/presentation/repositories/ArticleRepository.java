package com.projet.ressources.models.metier.presentation.repositories;

import com.projet.ressources.models.beans.articles.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Article a SET a.image = :image WHERE a.id = :id")
    public void updateBackgroundImage(@Param("image") byte[] image, @Param("id") Integer id);

    @Transactional
    @Query("SELECT image FROM Article WHERE id = :id")
    public byte[] getImageByArticleId(@Param("id") Integer articleId);
}
