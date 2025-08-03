package com.form_login.form_login.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class); 

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {        
        http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/public").permitAll()
                .requestMatchers("/api/user").hasRole("USER")
                .requestMatchers("/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());

        logger.info("SecurityFilterChain configurado com sucesso!");
		return http.build();
        
    }


    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("Configurando UserDetailsService...");
        
        UserDetails user = User.builder()
            .username("user")
            .password("{noop}12345")
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password("{noop}ulisses")
            .roles("USER", "ADMIN")
            .build();

        logger.debug("Usuário criado: {}", user.getUsername() + " com senha: " + user.getPassword());
        logger.debug("Administrador criado: {}", admin.getUsername() + " com senha: " + admin.getPassword());
        
        return new InMemoryUserDetailsManager(user, admin);
    }
}
