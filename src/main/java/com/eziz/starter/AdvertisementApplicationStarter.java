package com.eziz.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.eziz")
@ComponentScan(basePackages = "com.eziz")
@EnableJpaRepositories(basePackages = "com.eziz")
public class AdvertisementApplicationStarter {
    
    public static void main(String[] args) {
        
        SpringApplication.run(AdvertisementApplicationStarter.class, args);
        
    }

}
