package com.projet.ressources.models.metier.systeme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.ressources.models.beans.Groupe;


@Repository
public interface GroupRepository extends JpaRepository<Groupe,Long> {
	
	public List<Groupe> findAllByOrderById(); 
	public Groupe findTopByOrderByIdDesc() ; 
}
