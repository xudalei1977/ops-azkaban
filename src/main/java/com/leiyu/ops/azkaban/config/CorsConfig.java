package com.leiyu.ops.azkaban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Set configuration for Cross-Origin Resource Sharing (CORS)
 *
 * @author Pitt
 * @date 2020-01-03
 */
@Configuration
public class CorsConfig {

    // Return a new CorsConfiguration with given configuration items
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    // Bean: CorsFilter
    // Singleton
    // 1. Create a new CorsConfiguration with following configuration items:
    // 1) Allow all origin URLs
    // 2) Allow all origin headers
    // 3) Allow all origin methods
    // 4) Allow carrying credentials
    // 2. Register the CorsConfiguration for all of APIs calling
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
