package com.example.lesson_12.services;

import com.example.lesson_12.entities.Ticket;
import com.example.lesson_12.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ticketRepository.findById(id);
    }
}
