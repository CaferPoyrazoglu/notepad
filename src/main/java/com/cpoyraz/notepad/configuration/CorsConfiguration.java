package com.cpoyraz.notepad.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://notepad.cfapps.us10-001.hana.ondemand.com",
                                "https://notepad.cfapps.us10-001.hana.ondemand.com",
                                "http://notepadtest.netlify.app",
                                "https://notepadtest.netlify.app",
                                "http://localhost:5173",
                                "http://localhost:8989"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true).maxAge(3600)
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                HttpHeaders.AUTHORIZATION, "token");
            }
        };
    }

}

