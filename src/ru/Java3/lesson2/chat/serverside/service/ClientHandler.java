package ru.Java3.lesson2.chat.serverside.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientHandler {

    private MyServer myServer;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String name;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost/mytestbase";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                    changeNick();

                } catch (IOException ignored) {

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();


        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problem with ClientHandler");
        }
    }


    public void changeNick() throws Exception {
        while (true) {

            String str = dis.readUTF();
            if (str.startsWith("/change")) {
                String[] arr = str.split("\\s");
                String newnick = arr[1];

                try {
                    Class.forName(DRIVER);
                    try (Connection conn = DriverManager.getConnection(DB, USER, PASSWORD)) {

                        PreparedStatement preparedStatement = null;
                        preparedStatement = conn.prepareStatement("INSERT INTO user (login, password, nick) VALUES (?, ?, ?)");
                        System.out.println("Обновление завершено");
                        preparedStatement.setString(3, newnick);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        return;
                        //Statement statement = conn.createStatement();
                        //ResultSet resultSet = statement.executeQuery(sql);
                        //statement.executeUpdate("UPDATE users SET nick = 'cool ' WHERE nick = 'vovochka'");

//                        while (resultSet.next()) {
//
//                            String snick = resultSet.getString("nick");
//
//
//                            if (snick.equals(name)) {
//                                statement.executeUpdate("UPDATE users SET nick = " + newnick + " WHERE nick = " + name);
//
//                                name = snick;
//                                System.out.println("новый ник" + name + " " + snick);
//                                conn.close();
//                                break;
//                            }
//                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = dis.readUTF();
            if (str.startsWith("/auth")) {
                String[] arr = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginAndPassword(arr[1], arr[2]);

                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMessage("/authOK " + nick);
                        name = nick;
                        myServer.broadcastMessage("Hello " + name);
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMessage("Nick is busy");
                    }

                } else {
                    sendMessage("Wrong login and password");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {

            dos.writeUTF(message);


        } catch (IOException ignored) {

        }

    }


    public void readMessage() throws IOException {
        while (true) {
            String messageFromClient = dis.readUTF();
            System.out.println(name + " send message " + messageFromClient);
            if (messageFromClient.trim().startsWith("/")) {
                if (messageFromClient.startsWith("/w")) {
                    String[] arr = messageFromClient.split(" ");

                    myServer.sendOnlyYou(name, messageFromClient, arr[1]);
                }
                if (messageFromClient == "/end") {
                    return;
                }
            } else {
                myServer.broadcastMessage(name + " : " + messageFromClient);
            }
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMessage(name + " Leave Chat");
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ignored) {

        }


    }

    public String getName() {
        return name;
    }

}
