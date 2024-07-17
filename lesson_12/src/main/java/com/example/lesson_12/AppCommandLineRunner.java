package com.example.lesson_12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired(required = false)
    private ThisIsMyFirstConditionalBean conditionalBean;

    @Override
    public void run(String... args) throws Exception {
        if (conditionalBean != null) {
            conditionalBean.printMessage();
        } else {
            System.out.println("The conditional bean was not created.");
        }
    }
}
