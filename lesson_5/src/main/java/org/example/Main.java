package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BusTicketsReader reader = new JsonBusTicketsReader("src/main/resources/BusTickets.json");
        BusTicketViolationTracker violationTracker = new BusTicketViolationTracker();
        List<BusTicket> tickets = reader.getValues();

        for (BusTicket ticket : tickets) {

            System.out.println(ticket);

            if (!BusTicketValidator.isValidTicketType(ticket)) {
                violationTracker.trackTypeViolation(ticket);
                System.out.println("The type of the ticket is not valid: " + ticket.getTicketType());
            }

            if (!BusTicketValidator.isValidTicketDate(ticket)) {
                violationTracker.trackDateViolation(ticket);
                System.out.println("The date of the ticket is not valid: " + ticket.getTicketDate());
            }

            if (!BusTicketValidator.isValidTicketPrice(ticket)) {
                violationTracker.trackPriceViolation(ticket);
                System.out.println("The price of the ticket is not valid: " + ticket.getPrice());
            }

            System.out.println();
        }

        int ticketsCount = tickets.size();
        int validTickets = ticketsCount - violationTracker.getInvalidTicketsCount();
        String popularViolation = violationTracker.buildMostPopularViolationsReport();

        System.out.println("Total = " + ticketsCount + "\n" +
                "Valid =  " + validTickets + "\n" +
                "Most popular violation = " + popularViolation);
    }
}