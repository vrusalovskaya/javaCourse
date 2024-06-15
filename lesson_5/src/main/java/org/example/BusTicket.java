package org.example;

public class BusTicket {
    private String ticketClass;
    private String ticketType;
    private String ticketDate;
    private String price;

    public BusTicket(String ticketClass, String ticketType, String ticketDate, String price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.ticketDate = ticketDate;
        this.price = price;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
