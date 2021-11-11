package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        Util util = new Util();

//        Util.getConnection();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("fff", "ggg", (byte) 45);

        List<User> toPr = userDaoJDBC.getAllUsers();
        System.out.println(toPr);

        userDaoJDBC.dropUsersTable();

        List<User> toPr2 = userDaoJDBC.getAllUsers();
        System.out.println(toPr2);

    }
}
