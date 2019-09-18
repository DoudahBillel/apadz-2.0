package com.projet.ressources.controllers.ressources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/ressources")
public class RessourcesController {
	
	@RequestMapping("")
	public String home() {
		return "Ressources"; 
	}

}
