package com.projet.ressources.models.metier.systeme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.ressources.models.beans.Compte;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
	long countByTypeCompte(int type) ;

	public List<Compte> findByTypeCompte(int type) ; 
	public List<Compte> findAllByOrderById() ; 
	public Compte findTopByOrderByIdDesc(); 
	public Compte findByUserName(String userName) ; 

}
