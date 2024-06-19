package org.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Validator {

    public static boolean isValidTicketType(BusTicket ticket){
        return Objects.equals(ticket.getTicketType(), "DAY")
                || Objects.equals(ticket.getTicketType(), "WEEK")
                || Objects.equals(ticket.getTicketType(), "MONTH")
                || Objects.equals(ticket.getTicketType(), "YEAR");
    }

    public static boolean isValidTicketDate(BusTicket ticket){
        return ticket.getTicketDate() != null && !Objects.equals(ticket.getTicketDate().trim(), "") && isDateInPast(ticket.getTicketDate());
    }

    public static boolean isValidTicketPrice(BusTicket ticket){
        if (ticket.getPrice() != null && !ticket.getPrice().trim().isEmpty()){
        double price = Double.parseDouble(ticket.getPrice());
        return !Objects.equals(ticket.getPrice(), "0") && price % 2 == 0;}
        else return false;
    }

    private static boolean isDateInPast(String date){
        String [] array = date.split("-");
        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        Date dateNow = java.sql.Date.valueOf(java.time.LocalDate.now());
        Date startDate = java.sql.Date.valueOf(getLocalDate(year, month, day));

        return dateNow.after(startDate);
    }

    static LocalDate getLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
