package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String INSERT_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private static String tableCreation
        = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT,"
        + " name varchar(20),  lastName varchar(20), age TINYINT)";


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(tableCreation);
//            connection.commit();  //auto-commit же
            System.out.println("Table has been created!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction tableCreation is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
//            connection.commit();
            System.out.println("Database has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction DROP TABLE is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement
                 = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
//            connection.commit();
            System.out.println("User " + name + " is in the table!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction INSERT_USER is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement
                 = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            connection.commit();
            System.out.println("User " + id + " deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction DELETE is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement
                 = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
//            connection.commit();

            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
//            connection.commit();
            System.out.println("Database has been cleaned!");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                System.err.print("Transaction CLEAN TABLE is being rolled back");
                connection.rollback();
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }
}

