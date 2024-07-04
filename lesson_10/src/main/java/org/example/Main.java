package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        UserDao userDao = new UserDao("jdbc:postgresql://localhost:5432/my_ticket_service_db", "postgres", "postgres42");
        System.out.println(userDao.createUser("Melisa"));
        userDao.getUserById(7).ifPresent(System.out::println);

        TicketDao ticketDao = new TicketDao("jdbc:postgresql://localhost:5432/my_ticket_service_db", "postgres", "postgres42");
        System.out.println(ticketDao.createTicket(4, TicketType.WEEK));
        ticketDao.getTicketById(2).ifPresent(System.out::println);
        for (Ticket ticket : ticketDao.getTicketsByUserId(2)) {
            System.out.println(ticket);
        }

        ticketDao.updateTicketType(1, TicketType.YEAR);
        ticketDao.getTicketById(1).ifPresent(System.out::println);

       userDao.deleteUserById(3);
    }
}