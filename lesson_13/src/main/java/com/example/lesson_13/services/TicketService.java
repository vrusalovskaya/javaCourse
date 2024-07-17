package com.example.lesson_13.services;

import com.example.lesson_13.entities.Ticket;
import com.example.lesson_13.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Optional<Ticket> getTicketById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        return ticketRepository.findById(id);
    }
}
