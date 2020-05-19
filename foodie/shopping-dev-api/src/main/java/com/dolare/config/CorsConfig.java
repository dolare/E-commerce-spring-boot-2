package com.dolare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        // set if sending the cookie information
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        // set path for the URL
        UrlBasedCorsConfigurationSource crosSource = new UrlBasedCorsConfigurationSource();
        crosSource.registerCorsConfiguration("/**", config);

        // return redefined source
        return new CorsFilter(crosSource);
    }
}
