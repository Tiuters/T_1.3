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

        userService.saveUser("Ищи", "ThatWasBob", (byte) 45);
        userService.saveUser("Tom", "A", (byte) 20);
        userService.saveUser("B", "Bb", (byte) 25);
        userService.saveUser("Вовочка", "H", (byte) 10);

        List<User> toPr = userService.getAllUsers();
        System.out.println(toPr);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
