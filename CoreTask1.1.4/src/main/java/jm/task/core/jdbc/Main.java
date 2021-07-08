package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Nikita", "Nikitin", (byte) 30);
        userService.saveUser("Vanya", "Petrov", (byte) 12);
        userService.saveUser("Petya", "Frolov", (byte) 34);
        userService.saveUser("Jenya", "Ivanov", (byte) 102);

        for (User s : userService.getAllUsers()) {
            System.out.println(s);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
