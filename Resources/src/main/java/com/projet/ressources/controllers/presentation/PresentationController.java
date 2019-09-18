package com.projet.ressources.controllers.presentation;

import com.projet.ressources.models.beans.articles.Article;
import com.projet.ressources.models.metier.presentation.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/")
public class PresentationController {

	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping("/")
	public String home(Model model) {
		return "general/presentation/index";
	}

	@RequestMapping("/dashboard/articles")
	public String modal(Model model) {
		model.addAttribute("page_title", "Liste des articles");
		model.addAttribute("main_content", "general/presentation/dashboard/contents/liste_articles");
		model.addAttribute("active_nav_link", "articles_link");

		return "general/presentation/dashboard/dashboard";
	}

	@RequestMapping("/dashboard/nouvel_article")
	public String nouvelArticle(Model model) {
		model.addAttribute("page_title", "Nouvel article");
		model.addAttribute("main_content", "general/presentation/dashboard/contents/nouvel_article");
		model.addAttribute("active_nav_link", "nouvel_article_link");

		return "general/presentation/dashboard/dashboard";
	}

	@RequestMapping("/dashboard/update_article/{id}")
	public String updateArticle(Model model, @PathVariable("id") Integer idArticle){
		Optional<Article> optionalArticle = this.articleRepository.findById(idArticle);

		if(!optionalArticle.isPresent())
			return this.nouvelArticle(model);

		Article article = optionalArticle.get();

		model.addAttribute("page_title", "Mise à jour de l'article " + article.getTitle());
		model.addAttribute("main_content", "general/presentation/dashboard/contents/update_article");
		model.addAttribute("active_nav_link", "nouvel_article_link");
		model.addAttribute("articleId", article.getId());

		return "general/presentation/dashboard/dashboard";
	}

	@RequestMapping("/article_description/{id}")
    public String articleDescription(Model model, @PathVariable("id") Integer idArticle){
        Optional<Article> optionalArticle = this.articleRepository.findById(idArticle);

        if(!optionalArticle.isPresent())
            return this.home(model);

        Article article = optionalArticle.get();

        model.addAttribute("articleId", article.getId());

        return "general/presentation/describe_article";
    }
}
