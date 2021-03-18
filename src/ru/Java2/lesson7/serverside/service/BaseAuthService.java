package ru.Java2.lesson7.serverside.service;

import ru.Java2.lesson7.serverside.interfaces.AuthService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseAuthService implements AuthService {

    //  private List<Entry> entryList;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost/mytestbase";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public BaseAuthService() {

    }

    @Override
    public void start() {
        System.out.println("AuthService start");
    }

    @Override
    public void stop() {
        System.out.println("AuthService stop");
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {

        String sql = "SELECT * FROM users";
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD);) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String slogin = resultSet.getString("login");
                    String spassword = resultSet.getString("password");
                    String snick = resultSet.getString("nick");
                    if (slogin.equals(login) && spassword.equals(password)) {
                        conn.close();
                        return snick;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }
}
