package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(rollbackFor = Exception.class)
    public int createTicket(User user) {
        Session session = sessionFactory.openSession();
        Ticket ticket = new Ticket();
        ticket.setUser(user);

        session.persist(ticket);
        return ticket.getId();
    }
}
