package com.vishalxbhargav.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChain {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Define CORS configuration
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173"); // Allow your frontend URL (React, Angular, etc.)
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedHeader("*"); // Allow all headers
        corsConfig.setAllowCredentials(true); // Allow credentials like cookies or authorization headers

        // Apply configuration globally
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Apply to all endpoints

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors() // Enable CORS in Spring Security
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll() // Allow all endpoints for testing (remove or restrict in production)
                .anyRequest().authenticated(); // Secure other endpoints

        return http.build();
    }
}
