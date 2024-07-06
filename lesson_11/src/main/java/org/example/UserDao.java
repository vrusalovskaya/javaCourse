package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(rollbackFor = Exception.class)
    public int createUser(String name) {
        Session session = sessionFactory.openSession();
        User user = new User();
        user.setName(name);
        session.persist(user);
        return user.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean activateUserAndCreateTicket(int id) {
        Session session = sessionFactory.openSession();
        String hql = "FROM " + User.class.getCanonicalName() + " U WHERE U.id = :passed_id";
        var query = session.createSelectionQuery(hql, User.class);
        query.setParameter("passed_id", id);
        var user = query.getSingleResultOrNull();

        user.setIsActive(true);
        session.persist(user);

        TicketDao ticketDao = new TicketDao(this.sessionFactory);
        ticketDao.createTicket(user);
        return true;
    }
}
