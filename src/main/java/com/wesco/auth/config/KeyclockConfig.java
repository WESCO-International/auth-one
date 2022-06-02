package com.wesco.auth.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class KeyclockConfig {

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {

        return new KeycloakSpringBootConfigResolver();
    }

}
