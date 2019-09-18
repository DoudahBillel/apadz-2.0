package com.projet.ressources.models.metier.systeme.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.ressources.models.beans.Type;
import com.projet.ressources.models.metier.systeme.repositories.TypeRepository;

@Service
public class TypeService {
	
	
	@Autowired
	private TypeRepository typeRepository ; 
	
	
	public List<Type> findAll(){
		return this.typeRepository.findAll() ; 
	}

}
