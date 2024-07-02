package org.example;

import java.io.IOException;
import java.util.List;

public abstract class BusTicketsReader {
    private final String path;

    protected BusTicketsReader(String path) {
        this.path = path;
    }

    public abstract List<BusTicket> getValues() throws IOException;

    protected String getPath() {
        return path;
    }
}
