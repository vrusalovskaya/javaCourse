package org.example;

import java.util.ArrayList;

public class Client extends User{

    private final ArrayList<Ticket> tickets;
    private final String phone;
    private final String email;

   public Client(String phone, String email){
        this.phone = phone;
        this.email = email;
        this.tickets = new ArrayList<>();
    }

    @Override
    public void printRole() {
        System.out.println("I'm client");
    }

    public void getTicket (Ticket ticket){
        tickets.add(ticket);
    }
}
