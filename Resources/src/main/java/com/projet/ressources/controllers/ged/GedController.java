package com.projet.ressources.controllers.ged;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ged")
public class GedController {
	
	@RequestMapping("")
	public String home() {
		return "GED"; 
	}

}
