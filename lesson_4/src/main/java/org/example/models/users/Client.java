package org.example.models.users;

import org.example.models.tickets.Ticket;

import java.util.ArrayList;

public class Client extends User {

    private final ArrayList<Ticket> tickets;

    public Client(int id, String name) {
        super(id, name);
        this.tickets = new ArrayList<>();
    }

    @Override
    public void printRole() {
        System.out.println("I'm client");
    }

    public void getTicket(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket with ID " + ticket.getId() + " is added to " + this.getName() + "'s storage");
    }
}
