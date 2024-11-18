package com.vishalxbhargav.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.vishalxbhargav.repository")
@EntityScan(basePackages = "com.vishalxbhargav.model")  // Ensure this points to your entity package
public class JpaConfig {
    // JPA-related configuration goes here if necessary
}
