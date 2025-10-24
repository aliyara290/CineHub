package com.cenihub;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

public class ConfigTest {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext("com.cenihub.config")) {

            DataSource dataSource = context.getBean(DataSource.class);
            System.out.println("DataSource: " + dataSource);

            LocalContainerEntityManagerFactoryBean emf =
                    context.getBean(LocalContainerEntityManagerFactoryBean.class);
            System.out.println("EntityManagerFactory: " + emf);

            System.out.println("Configuration loaded successfully!");
        }
    }
}