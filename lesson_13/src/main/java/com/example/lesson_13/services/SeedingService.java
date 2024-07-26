package com.example.lesson_13.services;

import com.example.lesson_13.entities.Ticket;
import com.example.lesson_13.entities.TicketType;
import com.example.lesson_13.entities.User;
import com.example.lesson_13.repositories.TicketRepository;
import com.example.lesson_13.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class SeedingService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public SeedingService(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public void seed() {
        User user = new User();
        user.setName("Tom");
        user.setActive(true);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setType(TicketType.DAY);

        userRepository.save(user);
        ticketRepository.save(ticket);
    }
}
