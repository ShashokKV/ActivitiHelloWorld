package com.activiti.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class BeanConfig {
    @Value("${spring.mvc.view.prefix}")
    private String resourcePrefix;
    @Value("${spring.mvc.view.suffix}")
    private String resourceSuffix;
    @Value("${spring.mvc.view.resolver.class}")
    private String resolverClassName;

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        try {
            System.out.println("started BeanConfig");
            Class<?> resolverClass = Class.forName(resolverClassName);
            InternalResourceViewResolver resolver= new InternalResourceViewResolver();
            resolver.setPrefix(resourcePrefix);
            resolver.setSuffix(resourceSuffix);
            resolver.setViewClass(resolverClass);
            return resolver;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Unckown resolver class: " + resolverClassName);
        }
    }
}