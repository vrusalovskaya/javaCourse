package org.example;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class AppSettings {

    private final Environment environment;

    public AppSettings(Environment environment) {
        this.environment = environment;
    }

    public Boolean isUpdateUserAndCreateTicketEnabled() {
        return environment.getRequiredProperty("app.feature.update-user-and-create-ticket.enabled", Boolean.class);
    }
}
