package com.projet.ressources.configs;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.beans.articles.CategorieArticle;
import com.projet.ressources.models.metier.presentation.repositories.CategorieArticleRepository;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
public class OnDatabaseFirstCreationListener {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private CategorieArticleRepository categorieArticleRepository;

	@PostConstruct
	public void fillDatabaseWithInitialData() {
		if (this.isFirstCreation()) {
			Compte compte = new Compte();
			compte.setUserName("administrateur");
			compte.setPassword(this.passwordEncoder.encode("administrateur"));
			compte.setLastName("Administrateur");
			compte.setFirstName("Administrateur");
			compte.setTypeCompte(4);

			this.compteRepository.save(compte);

			List<CategorieArticle> categorieArticleList = new LinkedList<>();
			categorieArticleList.add(new CategorieArticle(0, "Qui est l'APA DZ"));
			categorieArticleList.add(new CategorieArticle(1, "Liens"));
			categorieArticleList.add(new CategorieArticle(2, "Evenements à venir ou passés sur l'APA dans notre pays"));
			categorieArticleList.add(new CategorieArticle(3, "Historique du contenu"));
			categorieArticleList.add(new CategorieArticle(4, "Les nouvelles sur l'APA"));
			categorieArticleList.add(new CategorieArticle(5, "Mot du directeur et contact"));

			this.categorieArticleRepository.saveAll(categorieArticleList);
		}
	}

	public boolean isFirstCreation() {
		return this.compteRepository.count() == 0;
	}
}
