package com.com.seal.gym.context;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by xiaozli on 2017/6/26.
 */



public class GymWebAppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("Start up GYM!!!!!!!!!!!");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.scan("com");
        ServletRegistration.Dynamic registration =
                servletContext.addServlet("dispatcher",
                        new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("*.request");
    }
}
