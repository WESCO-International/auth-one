package com.wesco.auth.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


@KeycloakConfiguration
@Import(KeycloakSpringBootConfigResolver.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    private static String AUTH_LOGIN = "/auth/token";
    private static  String[] SWAGGER_RESOURCES = {"/v2/api-docs/**", "/v3/api-docs/**",
            "/swagger-ui/**","/actuator/**"};
    private static String[] SECURITY_IGNORE_RESOURCE = {"/*.js", "/*.css", "/*.ico",
            "/assets/**", "/static/**", "/swagger-ui/**",
            "/v2/api-docs/**", "/configuration/ui",
            "/swagger-resources/**", "/configuration/**",
            "/swagger-ui.html", "/webjars/**", "/swagger-ui/index.html",
            "/swagger.json", "/configuration/ui",
            "/v3/api-docs/**", "/v1/users/register",
            "/v1/users/**/validate/**"};

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
        SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
        simpleAuthorityMapper.setConvertToUpperCase(true);
        KeycloakAuthenticationProvider keycloakAuthenticationProvider
                = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(simpleAuthorityMapper);
        authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_LOGIN).permitAll()
                .antMatchers(SWAGGER_RESOURCES).permitAll()
                .anyRequest().authenticated();
       // http.addFilterBefore(authFilter, BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(SECURITY_IGNORE_RESOURCE);
    }

}