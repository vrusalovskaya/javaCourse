package com.example.lesson_12;

import com.example.lesson_12.services.SeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired(required = false)
    private SeedingService seedingService;

    @Override
    public void run(String... args) throws Exception {
        if (seedingService != null) {
            seedingService.seed();
        } else {
            System.out.println("The conditional bean was not created.");
        }
    }
}
