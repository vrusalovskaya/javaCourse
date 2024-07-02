package org.example;

import java.util.HashSet;

public class BusTicketViolationTracker {

    private int invalidTypeCount;
    private int invalidDateCount;
    private int invalidPriceCount;
    private final HashSet<BusTicket> invalidTickets = new HashSet<>();

    public void trackTypeViolation(BusTicket ticket){
        invalidTypeCount++;
        invalidTickets.add(ticket);
    }

    public void trackDateViolation(BusTicket ticket){
        invalidDateCount++;
        invalidTickets.add(ticket);
    }

    public void trackPriceViolation(BusTicket ticket){
        invalidPriceCount++;
        invalidTickets.add(ticket);
    }

    public String buildMostPopularViolationsReport() {

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

    public int getInvalidTicketsCount(){
        return invalidTickets.size();
    }
}
