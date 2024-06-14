package org.example;

import org.example.models.tickets.Currency;
import org.example.models.tickets.StadiumSector;
import org.example.models.tickets.Ticket;
import org.example.models.tickets.TicketPrice;
import org.example.models.users.Admin;
import org.example.models.users.Client;
import org.example.models.users.User;

import java.math.BigDecimal;

public class TicketService {
    public static void main(String[] args) {
        Ticket emptyTicket = Ticket.createEmpty();
        Ticket limitedTicket = Ticket.createLimited(
                new TicketPrice(BigDecimal.valueOf(100), Currency.USD),
                "Prime Hall",
                123,
                1728054000L);
        Ticket fullTicket = Ticket.create(
                new TicketPrice(BigDecimal.valueOf(200), Currency.EUR),
                "Re:public",
                321,
                1739206800L,
                false,
                StadiumSector.A,
                3.25);

        emptyTicket.print();
        limitedTicket.print();
        fullTicket.print();

        //static polymorphism (overloading)
        emptyTicket.share("+37529123456789");
        emptyTicket.share("+37529987654321", "test@example.com");

        Client tom = new Client(1, "Tom");
        Admin bob = new Admin(2, "Bob");

        // dynamic polymorphism (overriding)
        ((User) tom).printRole();
        ((User) bob).printRole();

        tom.setTicket(fullTicket);
        System.out.println(tom.getName() + "'s ticket: " + tom.getTicket());
        bob.checkTicket(emptyTicket, 123);

        Ticket testTicket = fullTicket;
        System.out.println(testTicket.equals(limitedTicket));
        System.out.println(testTicket.equals(fullTicket));
    }
}