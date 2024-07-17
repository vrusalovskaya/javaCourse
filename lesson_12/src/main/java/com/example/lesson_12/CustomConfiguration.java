package com.example.lesson_12;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean
    @ConditionalOnProperty(name = "app.custom-bean-enabled", havingValue = "true")
    public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
