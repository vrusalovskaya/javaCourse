package com.example.lesson_12;

import com.example.lesson_12.services.SeedingService;
import com.example.lesson_12.repositories.TicketRepository;
import com.example.lesson_12.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean
    @ConditionalOnProperty(name = "app.seeding-enabled", havingValue = "true")
    @Autowired
    public SeedingService thisIsMyFirstConditionalBean(UserRepository userRepository, TicketRepository ticketRepository) {
        return new SeedingService(userRepository, ticketRepository);
    }
}
