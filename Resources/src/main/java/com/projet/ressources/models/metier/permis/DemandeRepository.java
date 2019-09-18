package com.projet.ressources.models.metier.permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.ressources.models.beans.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
	
	
}
