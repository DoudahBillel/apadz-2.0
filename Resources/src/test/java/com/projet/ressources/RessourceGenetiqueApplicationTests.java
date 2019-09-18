package com.projet.ressources;

import com.projet.ressources.models.metier.presentation.repositories.ArticleRepository;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RessourceGenetiqueApplicationTests {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void deleteAllComptes() {
		compteRepository.deleteAll();
	}

	@Test
	public void deleteAllArticles() {
		this.articleRepository.deleteAll();
	}
}
