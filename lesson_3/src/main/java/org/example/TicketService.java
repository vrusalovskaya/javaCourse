package org.example;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private static final List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {
        Ticket emptyTicket = Ticket.createEmpty();
        Ticket limitedTicket = Ticket.createLimited(
                new TicketPrice(BigDecimal.valueOf(100), Currency.USD),
                "Musik",
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

        tickets.add(fullTicket);
        tickets.add(emptyTicket);
        tickets.add(limitedTicket);


        printTicket(emptyTicket);
        printTicket(limitedTicket);
        printTicket(fullTicket);
    }

    private static void printTicket(Ticket ticket) {
        System.out.println("Ticket ID: " + ticket.getId());
        System.out.println("Creation time: " + covertToHumanReadableDate(ticket.getCreationTime()));

        if (ticket.getPrice().isPresent() && ticket.getConcertHall().isPresent() && ticket.getEventCode().isPresent() && ticket.getTime().isPresent()) {
            System.out.println("Price: " + ticket.getPrice().get());
            System.out.println("Concert hall: " + ticket.getConcertHall().get());
            System.out.println("Event code: " + ticket.getEventCode().get());
            System.out.println("Time: " + covertToHumanReadableDate(ticket.getTime().get()));
        }

        if (ticket.isPromo().isPresent() && ticket.getSector().isPresent() && ticket.getAllowedBackpackWeight().isPresent()) {
            System.out.println("Promo ticket: " + ticket.isPromo().get());
            System.out.println("Stadium sector: " + ticket.getSector().get());
            System.out.println("Allowed backpack weight: " + formatWeight(ticket.getAllowedBackpackWeight().get()));
        }

        System.out.println();
    }

    private static String covertToHumanReadableDate(long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private static String formatWeight(double weight) {
        int kilograms = (int) weight;
        int grams = (int) ((weight - kilograms) * 1000);
        return kilograms + " kg " + grams + " g";
    }
    public Ticket findTicketByStadiumSector(StadiumSector sector) {
        return tickets.stream()
                .filter(ticket -> ticket.getSector().equals(sector))
                .findFirst().orElse(null);
    }

}
