package ru.Java3.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysql {

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost/mytestbase";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB, USER, PASSWORD);
            statement = conn.createStatement();
            final String insertSQL = "INSERT INTO users (id, login, password, nick)" + " VALUES (2, 'login1', 'password1', 'nick1')";
//            statement.executeUpdate("INSERT INTO users (login, password, nick) VALUES ('login1', 'password1', 'nick1')");
//            //statement.executeUpdate("DELETE FROM users");
//            statement.executeUpdate("INSERT INTO users (login, password, nick) VALUES ('login2', 'password1', 'nick2')");
//            statement.executeUpdate("INSERT INTO users (login, password, nick) VALUES ('login3', 'password1', 'nick3')");
            statement.executeUpdate("DELETE FROM users WHERE login = 'login1'");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
