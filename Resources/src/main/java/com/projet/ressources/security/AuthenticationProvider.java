package com.projet.ressources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserPrincipalDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String username = auth.getName();

        UserDetails user = this.userDetailsService.loadUserByUsername(username);

        if (user != null) {
            String password = auth.getCredentials().toString();

            if (this.passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("authentication");

                return new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
            }
        }

        System.out.println("bad credetials");

        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
