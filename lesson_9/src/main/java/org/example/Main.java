package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            UserDao userDao = new UserDao(factory);
            System.out.println(userDao.createUser("Bob"));
            userDao.getUserById(3).ifPresent(System.out::println);
            userDao.updateUserNameAndTypeOfHisTickets(3, "Anna", TicketType.DAY);
            userDao.getUserById(3).ifPresent(System.out::println);

            TicketDao ticketDao = new TicketDao(factory);
            System.out.println(ticketDao.createTicket(userDao.getUserById(3).get(), TicketType.YEAR));
            ticketDao.getTicketById(2).ifPresent(System.out::println);
            for (Ticket ticket : ticketDao.getTicketsByUser(userDao.getUserById(1).get())) {
                System.out.println(ticket);
            }

            ticketDao.updateTicketType(2, TicketType.YEAR);
            ticketDao.getTicketById(1).ifPresent(System.out::println);

            userDao.deleteUserById(5);
        }

    }
}