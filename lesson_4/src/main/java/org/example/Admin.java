package org.example;

import java.util.Objects;

public class Admin extends User{

    public Admin(String name){
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("I'm admin");
    }

    public void checkTicket(Ticket ticket, Integer eventCode){
        if(ticket.getEventCode().isPresent() && Objects.equals(eventCode, ticket.getEventCode().get())){
            System.out.println("The ticket with ID " + ticket.getId() + "is valid for the event " + eventCode);
        } else {
            System.out.println("The ticket with ID " + ticket.getId() + "is not valid for the event with code " + eventCode);
        }
    }
}
