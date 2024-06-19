package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonBusTicketsReader extends BusTicketsReader {
    private final ObjectMapper objectMapper;

    JsonBusTicketsReader(String path) {
        super(path);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public ArrayList<BusTicket> getValues() throws IOException {
        return this.objectMapper.readValue(new File(this.getPath()), new TypeReference<>() {
        });
    }
}
