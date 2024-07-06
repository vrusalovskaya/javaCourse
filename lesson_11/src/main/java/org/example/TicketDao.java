package org.example;

import org.hibernate.SessionFactory;

public class TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
