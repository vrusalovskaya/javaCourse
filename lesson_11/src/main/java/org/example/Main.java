package org.example;

import org.example.configuration.AppConfiguration;
import org.example.entities.BusTicket;
import org.example.entities.TicketType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        UserService userService = context.getBean(UserService.class);
        BusTicketService busTicketService = context.getBean(BusTicketService.class);

        BusTicket[] tickets = busTicketService.loadTicketsFromJsonFile();
        for (BusTicket ticket : tickets) {
            System.out.println(ticket);
        }

        int id = userService.createUser("Lisa42");
        System.out.println(id);

        System.out.println(userService.activateUserAndCreateTicket(id, TicketType.DAY));
    }
}