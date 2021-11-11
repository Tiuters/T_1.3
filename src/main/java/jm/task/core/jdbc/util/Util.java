package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String dbURL = "jdbc:mysql://localhost:3306/pp1_schema"; //?autoReconnect=true&useSSL=false
    private static String dbUsername = "root";
    private static String dbPassword = "ert45dfc67";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
}
