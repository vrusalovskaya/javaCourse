package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = context.getBean(UserDao.class);
        TicketDao ticketDao = context.getBean(TicketDao.class);


        userDao.createUser("Mike");
        userDao.activateUserAndCreateTicket(1);

    }
}