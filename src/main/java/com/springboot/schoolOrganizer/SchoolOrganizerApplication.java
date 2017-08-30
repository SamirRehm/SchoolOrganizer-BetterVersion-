package com.springboot.schoolOrganizer;

import com.springboot.schoolOrganizer.web.SchoolOrganizerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolOrganizerApplication {

    @Bean
    public SchoolOrganizerFacade schoolOrganizerFacade() {
        return new SchoolOrganizerFacade();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SchoolOrganizerApplication.class, args);
    }
}
