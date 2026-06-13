package com.eziz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    public static final String uploadImage = "/rest/images/upload";
    public final static String uploadAdvertisement = "/rest/advertisement/upload";
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        
        
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.requestMatchers(uploadImage, uploadAdvertisement)
                    .authenticated().anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}