package org.example;

import java.sql.Timestamp;

public class Ticket {
    private final int id;
    private final int userId;
    private final Timestamp creationDate;

    private TicketType ticketType;

    public Ticket(int id, int userId, TicketType ticketType, Timestamp creationDate) {
        this.id = id;
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "id = " + id +
                ", userId = " + userId +
                ", ticketType = '" + ticketType.toString() + '\'' +
                ", creationDate = '" + creationDate + '\'' +
                '}';
    }

}
