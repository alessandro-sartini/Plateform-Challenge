package com.spring.restfinder.rest_finder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsCfg {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins(
            "https://TUO-FRONTEND.netlify.app",
            "https://TUO-FRONTEND.vercel.app"
          )
          .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");
      }
    };
  }
}

