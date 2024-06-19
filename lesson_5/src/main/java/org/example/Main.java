package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        BusTicketsReader reader = new JsonBusTicketsReader("src/main/resources/BusTickets.json");
        ArrayList<BusTicket> tickets = reader.getValues();

        int invalidTypeCounter = 0;
        int invalidDateCounter = 0;
        int invalidPriceCounter = 0;
        ArrayList<BusTicket> invalidTickets = new ArrayList<>();
        int totalOfTickets = tickets.size();

        for (BusTicket ticket : tickets) {

            System.out.println(ticket);

            if (!Validator.isValidTicketType(ticket)) {
                invalidTypeCounter++;
                addIfAbsent(invalidTickets, ticket);
                System.out.println("The type of the ticket is not valid: " + ticket.getTicketType());
            }

            if (!Validator.isValidTicketDate(ticket)) {
                invalidDateCounter++;
                addIfAbsent(invalidTickets, ticket);
                System.out.println("The date of the ticket is not valid: " + ticket.getTicketDate());
            }

            if (!Validator.isValidTicketPrice(ticket)) {
                invalidPriceCounter++;
                addIfAbsent(invalidTickets, ticket);
                System.out.println("The price of the ticket is not valid: " + ticket.getPrice());
            }

            System.out.println();
        }

        int validTickets = totalOfTickets - invalidTickets.size();

        String popularViolation = getString(invalidTypeCounter, invalidDateCounter, invalidPriceCounter);

        System.out.println("Total = " + totalOfTickets + "\n" +
                "Valid =  " + validTickets + "\n" +
                "Most popular violation = " + popularViolation);
    }

    private static void addIfAbsent(ArrayList<BusTicket> invalidTickets, BusTicket ticket) {
        if (!invalidTickets.contains(ticket)) {
            invalidTickets.add(ticket);
        }
    }

    private static String getString(int invalidTypeCounter, int invalidDateCounter, int invalidPriceCounter) {
        String popularViolation = "";

        if (invalidTypeCounter == 0 && invalidDateCounter == 0 && invalidPriceCounter == 0) {
            popularViolation = "none";
        } else if (invalidTypeCounter >= invalidDateCounter && invalidTypeCounter >= invalidPriceCounter) {
            popularViolation = popularViolation + "ticket type ";
        } else if (invalidDateCounter >= invalidTypeCounter && invalidDateCounter >= invalidPriceCounter) {
            popularViolation = popularViolation + "start date ";
        } else if (invalidPriceCounter >= invalidDateCounter && invalidPriceCounter >= invalidTypeCounter) {
            popularViolation = popularViolation + "price ";
        }

        return popularViolation;
    }
}