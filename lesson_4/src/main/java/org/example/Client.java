package org.example;

import java.util.ArrayList;

public class Client extends User{

    private ArrayList<Ticket> tickets;

    @Override
    public void printRole() {
        System.out.println("I'm client");
    }

    public void getTicket (Ticket ticket){
        tickets.add(ticket);
    }
}
