package com.bernardo.ListaUsuarios.bernardo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DbConnectionInterceptor dbInterceptor;

    public WebConfig(DbConnectionInterceptor dbInterceptor) {
        this.dbInterceptor = dbInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dbInterceptor)
                .addPathPatterns("/users/**", "/books/**", "/cars/**");
    }
}