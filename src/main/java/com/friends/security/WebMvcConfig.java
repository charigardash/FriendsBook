package com.friends.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECS = 3600;

//    @Value("${app.cors.allowedOrigins}")
//    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
                        "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
                .allowedOrigins("http://localhost:3000","https://myfriendsbook.netlify.app")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
        
    }
}
