package com.example.lesson_13.services;

import com.example.lesson_13.entities.Ticket;
import com.example.lesson_13.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    private final Integer TICKET_ID = 1;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(TICKET_ID);
    }

    @Test
    void getTicketById_ticketExistsInRepository_returnsTicket() {
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.of(ticket));

        Optional<Ticket> foundTicket = ticketService.getTicketById(TICKET_ID);

        assertTrue(foundTicket.isPresent());
        assertEquals(ticket.getId(), foundTicket.get().getId());
        verify(ticketRepository, times(1)).findById(TICKET_ID);
    }

    @Test
    void getTicketById_ticketNotFoundInRepository_returnsOptionalEmpty() {
        when(ticketRepository.findById(TICKET_ID)).thenReturn(Optional.empty());

        Optional<Ticket> foundTicket = ticketService.getTicketById(TICKET_ID);

        assertFalse(foundTicket.isPresent());
        verify(ticketRepository, times(1)).findById(TICKET_ID);
    }

    @Test
    void getTicketById_nullAsIdParameter_throwsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.getTicketById(null);
        });

        assertEquals("id cannot be null", exception.getMessage());
    }
}