package org.example;

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
    }
}