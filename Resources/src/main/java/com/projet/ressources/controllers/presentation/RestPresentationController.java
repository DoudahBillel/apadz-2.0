package com.projet.ressources.controllers.presentation;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.articles.Article;
import com.projet.ressources.models.metier.presentation.repositories.ArticleRepository;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class RestPresentationController {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("compteServices/fetchLoggedInUserDetails")
    public Compte forceFetchLoggedInUserDetails() {
        Compte compte = this.fetchLoggedInUserDetails();

        if (compte != null) {
            return this.compteRepository.findById(compte.getId()).get();
        }

        return null;
    }

    @PostMapping("/uploadArticleBackgroundImage")
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idArticle") Integer articleId,
            HttpServletResponse response) throws IOException {

        Optional<Article> article = this.articleRepository.findById(articleId);

        if(article.isPresent()) {
            this.articleRepository.updateBackgroundImage(file.getBytes(), articleId);
        }

        response.sendRedirect("/dashboard/articles");
    }

    @ResponseBody
    @GetMapping("/articleBackground/{articleId}")
    public ResponseEntity<Resource> serveFile(@PathVariable("articleId") Integer idArticle) {

        byte[] imageAsArray = this.articleRepository.getImageByArticleId(idArticle);

        Resource file = null;

        if(imageAsArray != null)
            file = new ByteArrayResource(imageAsArray);
        else file = new ClassPathResource("static/images/index/ministere-culture.png");

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    public Compte fetchLoggedInUserDetails() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj != null && obj instanceof Compte)
            return (Compte) obj;

        return null;
    }
}
