package com.example.CRUDOperations.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	
	UserDetails admin=User.withUsername("Sundhar")
			              .password(encoder.encode("Password123"))
			              .roles("ADMIN")
			              .build();
	
   UserDetails user = User.withUsername("Yamini")
		                  .password(encoder.encode("Password321"))
		                  .roles("USER","CEO")
		                  .build();
	
	return new InMemoryUserDetailsManager(admin,user);
}




@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	return http.csrf(csrf->csrf.disable())
			   .authorizeHttpRequests(auth->auth.requestMatchers("/customers/findAll").permitAll())
			   .authorizeHttpRequests(auth->auth.requestMatchers("/customers/**").authenticated())
			   .formLogin(formLogin->formLogin.permitAll())
			   .build();	    
}



@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
	
}
