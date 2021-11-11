package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();


        userService.createUsersTable();
        userService.saveUser("fff", "ggg", (byte) 45);

        List<User> toPr = userService.getAllUsers();
        System.out.println(toPr);

        userService.dropUsersTable();

        List<User> toPr2 = userService.getAllUsers();
        System.out.println(toPr2);

    }
}
