package com.climbingapp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * CORS configuration to allow requests from Angular frontend
 * 
 * @author Climbing App
 * @version 1.0
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow credentials
        config.setAllowCredentials(true);
        
        // Allowed origins (Angular development server)
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "http://127.0.0.1:4200"
        ));
        
        // Allowed headers
        config.setAllowedHeaders(Arrays.asList(
            "Origin",
            "Content-Type",
            "Accept",
            "Authorization",
            "X-Requested-With",
            "Cache-Control"
        ));
        
        // Allowed HTTP methods
        config.setAllowedMethods(Arrays.asList(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH",
            "OPTIONS"
        ));
        
        // Expose headers
        config.setExposedHeaders(Arrays.asList(
            "Content-Type",
            "X-Total-Count"
        ));
        
        // Max age for preflight requests
        config.setMaxAge(3600L);
        
        // Apply configuration to all API routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}