package com.projet.ressources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.ressources.models.beans.Compte;
import com.projet.ressources.models.metier.systeme.repositories.CompteRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	@Autowired 
	private CompteRepository compteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Compte user = this.compteRepository.findByUserName(username) ;

		return user;
	}
}
