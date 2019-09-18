package com.projet.ressources.models.metier.ressources;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.ressources.models.beans.Demande;
import com.projet.ressources.models.beans.Ressource;

public interface RessourceRepository extends JpaRepository<Ressource, Long>  {
	
	Optional<Ressource> findById(Long id);

}
