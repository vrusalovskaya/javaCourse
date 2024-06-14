package org.example.models.users;

import org.example.models.tickets.Ticket;

import java.util.ArrayList;

public class Client extends User {

    private Ticket ticket;

    public Client(int id, String name) {
        super(id, name);
    }

    @Override
    public void printRole() {
        System.out.println("I'm client");
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public Ticket getTicket(){
        return ticket;
    }
}
