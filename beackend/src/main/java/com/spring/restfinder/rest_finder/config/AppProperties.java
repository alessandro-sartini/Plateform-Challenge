package com.spring.restfinder.rest_finder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

    private static String baseUrl;

    @Value("${app.base-url}")
    public void setBaseUrl(String url) {
        AppProperties.baseUrl = url;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
