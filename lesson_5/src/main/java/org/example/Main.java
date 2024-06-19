package org.example;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BusTicketsReader reader = new JsonBusTicketsReader("src/main/resources/BusTickets.json");
        List<BusTicket> tickets = reader.getValues();

        int invalidTypeCount = 0;
        int invalidDateCount = 0;
        int invalidPriceCount = 0;
        HashSet<BusTicket> invalidTickets = new HashSet<>();

        for (BusTicket ticket : tickets) {

            System.out.println(ticket);

            if (!BusTicketValidator.isValidTicketType(ticket)) {
                invalidTypeCount++;
                invalidTickets.add(ticket);
                System.out.println("The type of the ticket is not valid: " + ticket.getTicketType());
            }

            if (!BusTicketValidator.isValidTicketDate(ticket)) {
                invalidDateCount++;
                invalidTickets.add(ticket);
                System.out.println("The date of the ticket is not valid: " + ticket.getTicketDate());
            }

            if (!BusTicketValidator.isValidTicketPrice(ticket)) {
                invalidPriceCount++;
                invalidTickets.add(ticket);
                System.out.println("The price of the ticket is not valid: " + ticket.getPrice());
            }

            System.out.println();
        }

        int ticketsCount = tickets.size();
        int validTickets = ticketsCount - invalidTickets.size();

        String popularViolation = buildMostPopularViolationsMessage(invalidTypeCount, invalidDateCount, invalidPriceCount);

        System.out.println("Total = " + ticketsCount + "\n" +
                "Valid =  " + validTickets + "\n" +
                "Most popular violation = " + popularViolation);
    }

    private static String buildMostPopularViolationsMessage(
            int invalidTypeCount,
            int invalidDateCount,
            int invalidPriceCount) {

        if (invalidTypeCount == 0 && invalidDateCount == 0 && invalidPriceCount == 0) {
            return "none";
        }

        String popularViolation = "";
        if (invalidTypeCount >= invalidDateCount && invalidTypeCount >= invalidPriceCount) {
            popularViolation = "ticket type ";
        }

        if (invalidDateCount >= invalidTypeCount && invalidDateCount >= invalidPriceCount) {
            popularViolation = popularViolation + "start date ";
        }

        if (invalidPriceCount >= invalidDateCount && invalidPriceCount >= invalidTypeCount) {
            popularViolation = popularViolation + "price ";
        }

        return popularViolation;
    }
}