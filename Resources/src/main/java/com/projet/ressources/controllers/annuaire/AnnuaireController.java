package com.projet.ressources.controllers.annuaire;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/annuaire")
public class AnnuaireController {

	@RequestMapping("")
	public String home() {
		return "Anuuaire" ;
	}
}
 