package org.example;

import org.example.entities.Ticket;
import org.example.entities.TicketType;
import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createUser(String name) {
        Session session = getSession();
        User user = new User();
        user.setName(name);
        session.persist(user);
        return user.getId();
    }

    public User getUser(int id) {
        return getSession().get(User.class, id);
    }

    public int activateUserAndCreateTicket(User user, TicketType ticketType) {
        Session session = getSession();

        user.setIsActive(true);
        Ticket ticket = new Ticket();
        ticket.setType(ticketType);
        ticket.setUser(user);

        session.persist(user);
        session.persist(ticket);
        return ticket.getId();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
