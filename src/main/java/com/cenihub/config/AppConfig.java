package com.cenihub.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.cenihub")
@Import({DatabaseConfig.class, JpaConfig.class})
public class AppConfig {

}