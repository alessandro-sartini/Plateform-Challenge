package com.spring.restfinder.rest_finder.config; // Assicurati che il package sia corretto

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Deve corrispondere alla chiave in application.properties e ImageStorageService
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mappa l'URL "/uploads/**" al percorso fisico della directory esterna
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/"); // L' '/` finale è importante
    }
}