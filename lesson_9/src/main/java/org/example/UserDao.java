package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int createUser(String name) {
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();
            try {
                User user = new User();
                user.setName(name);
                session.persist(user);

                tx.commit();

                return user.getId();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public Optional<User> getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + User.class.getCanonicalName() + " U WHERE U.id = :passed_id";
            var query = session.createSelectionQuery(hql, User.class);
            query.setParameter("passed_id", id);
            var user = query.getSingleResultOrNull();
            return user == null ? Optional.empty() : Optional.of(user);
        }
    }

    public void deleteUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            try {
                String hql = "DELETE FROM " + User.class.getCanonicalName() + " U WHERE U.id = :passed_id";
                var query = session.createMutationQuery(hql);
                query.setParameter("passed_id", id);
                query.executeUpdate();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void updateUserNameAndTypeOfHisTickets(int id, String name, TicketType ticketType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            try {
                String hql = "FROM " + User.class.getCanonicalName() + " U WHERE U.id = :passed_id";
                var query = session.createSelectionQuery(hql, User.class);
                query.setParameter("passed_id", id);
                var user = query.getSingleResultOrNull();

                user.setName(name);
                user.getTickets().forEach(t -> t.setType(ticketType));

                session.persist(user);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }

        }
    }

}
