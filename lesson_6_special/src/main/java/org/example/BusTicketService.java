package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to manage bus tickets.
 */
public class BusTicketService {

    private final List<BusTicket> ticketStorage;

    /**
     * Constructs a new BusTicketService
     */
    public BusTicketService() {
        this.ticketStorage = new ArrayList<>();
    }

    /**
     * Creates a new bus ticket with the given parameters and adds it to the storage.
     *
     * @param id          the ID of the ticket
     * @param ticketClass the class of the ticket
     * @param type        the type of the ticket
     * @param date        the start date of the ticket
     * @param price       the price of the ticket
     * @return the created BusTicket
     */
    public BusTicket createBusTicket(String id, String ticketClass, String type, String date, String price) {
        BusTicket ticket = new BusTicket(id, ticketClass, type, date, price);
        this.ticketStorage.add(ticket);
        return ticket;
    }

    /**
     * Removes a bus ticket with the given ID from the storage.
     *
     * @param id the ID of the ticket to be removed
     */
    public boolean removeTicketById(String id) {
        return ticketStorage.removeIf(ticket -> Objects.equals(ticket.getId(), id));
    }

    /**
     * Retrieves a bus ticket with the given ID.
     *
     * @param id the ID of the ticket to retrieve
     * @return an Optional containing the found BusTicket, or an empty Optional if not found
     */
    public Optional<BusTicket> getTicketById(String id) {
        return ticketStorage.stream()
                .filter(ticket -> Objects.equals(ticket.getId(), id))
                .findFirst();
    }

    /**
     * Searches for bus tickets by their type.
     *
     * @param type the type of the tickets to search for
     * @return a list of BusTickets with the specified type
     */
    public List<BusTicket> searchByType(String type) {
        return ticketStorage.stream()
                .filter(ticket -> Objects.equals(ticket.getTicketType(), type))
                .collect(Collectors.toList());
    }

    /**
     * Searches for bus tickets within a specified price range.
     *
     * @param min the minimum price of the tickets to search for
     * @param max the maximum price of the tickets to search for
     * @return a list of BusTickets within the specified price range
     */
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