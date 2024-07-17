package com.example.lesson_12.repositories;

import com.example.lesson_12.entities.Ticket;
import org.springframework.data.repository.ListCrudRepository;

public interface TicketRepository extends ListCrudRepository<Ticket, Integer> {
}
