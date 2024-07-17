package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);

        System.out.println(userDao.createUser("Melisa"));
        userDao.getUserById(7).ifPresent(System.out::println);

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