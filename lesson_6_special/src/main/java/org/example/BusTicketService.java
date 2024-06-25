package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BusTicketService {

    private final List<BusTicket> ticketStorage;

    public BusTicketService() {
        this.ticketStorage = new ArrayList<>();
    }

    public BusTicket createBusTicket(String id, String type, String date) {
        BusTicket ticket = new BusTicket(id, "default", type, date, "10");
        this.ticketStorage.add(ticket);
        return ticket;
    }

    public void removeTicketById(String id) {
        ticketStorage.removeIf(ticket -> Objects.equals(ticket.getId(), id));
    }

    public Optional<BusTicket> getTicketById(String id) {
        return ticketStorage.stream()
                .filter(ticket -> Objects.equals(ticket.getId(), id))
                .findFirst();
    }

    public List<BusTicket> searchByType(String type) {
        return ticketStorage.stream()
                .filter(ticket -> Objects.equals(ticket.getTicketType(), type))
                .collect(Collectors.toList());
    }

    public List<BusTicket> searchByPrice(int min, int max) {
        return ticketStorage.stream()
                .filter(ticket -> {
                    try {
                        int price = Integer.parseInt(ticket.getPrice());
                        return price >= min && price <= max;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}