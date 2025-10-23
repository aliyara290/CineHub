package com.cenihub;

import com.cenihub.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CineHubApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("CinéHub Application Started!");
        System.out.println("Total Beans: " + context.getBeanDefinitionCount());
    }
}