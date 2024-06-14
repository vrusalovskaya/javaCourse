package org.example.models.tickets;

import java.math.BigDecimal;

public class TicketPrice {

    private final BigDecimal price;
    private final Currency currency;

    public TicketPrice(BigDecimal price, Currency currency) {

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        this.price = price;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return this.price + " " + currency;
    }
}
