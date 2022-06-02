package com.wesco.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.wesco.auth"})
@Configuration
@EnableJpaRepositories
public class AuthOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthOneApplication.class, args);
    }
}
