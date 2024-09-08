package com.programme.ProgramMe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        THIS COULD BE REMOVED AS IT IS REDUNDANT, SPRING SECURITY'S CORS POLICY TAKES PRECEDENCE AND WILL OVERRIDE THIS ANYWAY
//        registry.addMapping("/**")
//                .allowedOrigins("https://programmedev.netlify.app/") // Use the correct frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
}


