package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusTicket {
    private final String id;
    private String ticketClass;
    private String ticketType;
    private String ticketDate;
    private String price;

    @JsonCreator
    public BusTicket(
            @JsonProperty("id") String id,
            @JsonProperty("ticketClass") String ticketClass,
            @JsonProperty("ticketType") String ticketType,
            @JsonProperty("startDate") String ticketDate,
            @JsonProperty("price") String price) {
        this.id = id;
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.ticketDate = ticketDate;
        this.price = price;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", ticket class: " + this.getTicketClass() + ", type: " + this.getTicketType() + ", date: " + this.getTicketDate() + ", price: " + this.getPrice();
    }
}
