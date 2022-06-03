
package com.wesco.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme
                .JWT_BEARER_BUILDER
                .name("JWT")
                .build();
        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors.basePackage("com.wesco"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(Collections.singletonList(authenticationScheme))
                .enableUrlTemplating(false);
    }

    @Bean
    ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
                builder
                .title("Wesco Auth One Service")
                .version("1.0")
                .license("(C) Copyright wesco")
                .description("List of all user auth and management endpoints");
        return builder.build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("ADMIN", "Admin");
        authorizationScopes[1] = new AuthorizationScope("USER", "User");
        authorizationScopes[2] = new AuthorizationScope("SUPER_USER", "SUPER USER");
        return List.of(
                new SecurityReference("JWT", authorizationScopes));
    }
}
