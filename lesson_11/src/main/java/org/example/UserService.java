package org.example;

import org.example.entities.TicketType;
import org.example.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final AppSettings appSettings;
    private final UserDao userDao;

    public UserService(AppSettings appSettings, UserDao userDao) {
        this.appSettings = appSettings;
        this.userDao = userDao;
    }

    @Transactional()
    public int createUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name must not be empty");
        }

        return userDao.createUser(name);
    }

    @Transactional()
    public int activateUserAndCreateTicket(int userId, TicketType ticketType) {
        if (!appSettings.isUpdateUserAndCreateTicketEnabled()) {
            throw new UnsupportedOperationException("The feature to update User and create Ticket is disabled");
        }

        User user = userDao.getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("user not found " + userId);
        }

        return userDao.activateUserAndCreateTicket(user, ticketType);
    }
}
