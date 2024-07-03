package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createTicket(User user, TicketType type) {
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Ticket ticket = new Ticket();
                ticket.setUser(user);
                ticket.setType(type);

                session.persist(ticket);
                tx.commit();

                return ticket.getId();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }


    public Optional<Ticket> getTicketById(int id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + Ticket.class.getCanonicalName() + " T WHERE T.id = :passed_id";
            var query = session.createSelectionQuery(hql, Ticket.class);
            query.setParameter("passed_id", id);
            var result = query.getSingleResultOrNull();
            return result == null ? Optional.empty() : Optional.of(result);
        }
    }

    public List<Ticket> getTicketsByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + Ticket.class.getCanonicalName() + " T WHERE T.user = :passed_user";
            var query = session.createSelectionQuery(hql, Ticket.class);
            query.setParameter("passed_user", user);
            return query.list();
        }
    }

    public void updateTicketType(int id, TicketType newType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            try {
                String hql = "UPDATE " + Ticket.class.getCanonicalName() + " T SET type = :type WHERE T.id = :passed_id";
                var query = session.createMutationQuery(hql);
                query.setParameter("type", newType);
                query.setParameter("passed_id", id);
                query.executeUpdate();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
