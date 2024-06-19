package org.example;

import java.io.IOException;
import java.util.ArrayList;

public abstract class BusTicketsReader {
    private final String path;

    protected BusTicketsReader(String path) {
        this.path = path;
    }

    public abstract ArrayList<BusTicket> getValues() throws IOException;

    protected String getPath() {
        return path;
    }
}
