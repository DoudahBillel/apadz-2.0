package com.projet.ressources.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailsService userPrincipalDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(this.userPrincipalDetailsService);
	}
		
	@Override 
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf()
				.disable()
				.exceptionHandling()

				// --------------------------------------------------------------

				.and()
				.authenticationProvider(authenticationProvider())
				.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/dashboard/articles", true)
				.failureUrl("/?loginError")

				// --------------------------------------------------------------

				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)

				// --------------------------------------------------------------

				.and()
				.authorizeRequests()
				.antMatchers(
						"/login.html",
						"/login")
				.anonymous()

				// --------------------------------------------------------------

				.antMatchers(
						"/logout",
						"/dashboard/**",
						"/system/**")
				.authenticated()

				// --------------------------------------------------------------

				.antMatchers("/passwordRecovery",
						"/updatePassword")
				.hasAuthority("UPDATE_PASSWORD_AUTHORITY")

				// --------------------------------------------------------------

				.antMatchers(
						"/*",
						"/articleBackground/*",
						"/article_description/*",
						"/js/**",
						"/css/**",
						"/font/**",
						"/error/**",
						"/success/**",
						"/images/**",
						"/assets/**",
						"/resources/**",
						"/static/**",

						"/restapi/**",

						"/requestPasswordResetToken",
						"/changePassword")
				.permitAll()

				.anyRequest().authenticated();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider provider = new AuthenticationProvider();
		provider.setUserDetailsService(this.userPrincipalDetailsService);

		return provider;
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ; 
	}
}
