package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int totalOfTickets = 0;
        int invalidTypeCounter = 0;
        int invalidDateCounter = 0;
        int invalidPriceCounter = 0;

        ArrayList<BusTicket> invalidTickets = new ArrayList<>() ;

        int x = 0;
        do {
            String input = getInput();
            BusTicket ticket = ObjectMapper().readValue(input, BusTicket.class);
            totalOfTickets++;

            if (!Validator.isValidTicketType(ticket))
            {
                invalidTypeCounter++;
                addIfAbsent(invalidTickets, ticket);

            }

            if (!Validator.isValidTicketDate(ticket)){
                invalidDateCounter++;
                addIfAbsent(invalidTickets, ticket);
            }

            if (!Validator.isValidTicketPrice(ticket)){
                invalidPriceCounter++;
                addIfAbsent(invalidTickets, ticket);
            }

            System.out.println(ticket);
            x++;
        } while (x < 5);

        int validTickets = totalOfTickets - invalidTickets.size();

        String popularViolation = getString(invalidTypeCounter, invalidDateCounter, invalidPriceCounter);

        System.out.println("Total = " + totalOfTickets +
                "Valid =  " + validTickets +
                "Most popular violation = " + popularViolation);
    }

    private static String getInput(){
        return  new Scanner(System.in).nextLine();
    }

    private static void addIfAbsent(ArrayList<BusTicket> invalidTickets, BusTicket ticket){
        if (!invalidTickets.contains(ticket)){
            invalidTickets.add(ticket);
        }
    }

    private static String getString(int invalidTypeCounter, int invalidDateCounter, int invalidPriceCounter) {
        String popularViolation = "";

        if (invalidTypeCounter >= invalidDateCounter && invalidTypeCounter >= invalidPriceCounter){
            popularViolation = popularViolation + "ticket type ";
        } else if (invalidDateCounter >= invalidTypeCounter && invalidDateCounter >= invalidPriceCounter) {
            popularViolation = popularViolation + "start date ";
        }else if (invalidPriceCounter >= invalidDateCounter && invalidPriceCounter >= invalidTypeCounter) {
            popularViolation = popularViolation + "price ";
        }
        return popularViolation;
    }
}