package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.BusTicket;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BusTicketService {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public BusTicketService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    public BusTicket[] loadTicketsFromJsonFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:BusTickets.json");
        return objectMapper.readValue(resource.getInputStream(), BusTicket[].class);
    }
}
