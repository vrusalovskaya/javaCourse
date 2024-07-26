package com.example.lesson_13.repositories;

import com.example.lesson_13.entities.Ticket;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends ListCrudRepository<Ticket, Integer> {
}
