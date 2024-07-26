package org.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class BusTicketValidator {

    public static boolean isValidTicketType(BusTicket ticket) {
        return Objects.equals(ticket.getTicketType(), "DAY")
                || Objects.equals(ticket.getTicketType(), "WEEK")
                || Objects.equals(ticket.getTicketType(), "MONTH")
                || Objects.equals(ticket.getTicketType(), "YEAR");
    }

    public static boolean isValidTicketDate(BusTicket ticket) {
        return ticket.getTicketDate() != null
                && !ticket.getTicketDate().trim().isEmpty()
                && isDateBeforeFuture(ticket.getTicketDate());
    }

    public static boolean isValidTicketPrice(BusTicket ticket) {
        if (ticket.getPrice() != null && !ticket.getPrice().trim().isEmpty()) {
            double price = Double.parseDouble(ticket.getPrice());
            return Double.compare(price, 0.0) > 0 && price % 2 == 0;
        }

        return false;
    }

    private static boolean isDateBeforeFuture(String date) {
        String[] array = date.split("-");
        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        Date dateNow = java.sql.Date.valueOf(java.time.LocalDate.now());
        Date startDate = java.sql.Date.valueOf(getLocalDate(year, month, day));

        return !startDate.after(dateNow);
    }

    static LocalDate getLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
