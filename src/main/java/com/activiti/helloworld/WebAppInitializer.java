package com.activiti.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Value("${spring.application.name}")
    String appTitle;

    @Value("${spring.application.root.url}")
    String urlMapping;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);
        appContext.refresh();

        // Create DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(appContext);

        // Register and map the Servlet
        ServletRegistration.Dynamic registration = servletContext.addServlet(appTitle, servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping(urlMapping);

    }
}