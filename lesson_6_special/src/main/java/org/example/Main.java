package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

        BusTicketService service = new BusTicketService();
        BusTicket ticket1 = service.createBusTicket("22", "CLA", "MONTH","2020-01-01","100");
        BusTicket ticket2 = service.createBusTicket("23", "TFA", "MONTH","2020-01-01","10");
        BusTicket ticket3 = service.createBusTicket("24", "CLA", "MONTH","2020-01-01","200");

        System.out.println(service.removeTicketById("100"));
        System.out.println(service.removeTicketById("22"));

        System.out.println(service.getTicketById("100"));
        System.out.println(service.getTicketById("23"));

        System.out.println(service.searchByType("AAA"));
        System.out.println(service.searchByType("MONTH"));

        System.out.println(service.searchByPrice(40,50));
        System.out.println(service.searchByPrice(100,300));

    }
}