package com.gridsocial.config;

import com.gridsocial.WebtestApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//i will work on this -Ethan M.
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SecurityConfig {
    public static void main(String[] args) {
        SpringApplication.run(WebtestApplication.class, args);
    }
}