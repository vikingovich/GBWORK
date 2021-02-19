package ru.Java2.lesson7.serverside.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private MyServer myServer;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String name;

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

                } catch (IOException ignored) {

                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problem with ClientHandler");
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
                        sendMessage("/authOK" + nick);
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
//            if (str.startsWith("/w")) {
//                String[] arr = str.split("\\s");
//                String nick = myServer.getAuthService().getNickByLoginAndPassword(arr[1], arr[2]);
//                sendMessage(myServer.onlySendYou(str, nick));
//                return;
//            }
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
            }else {
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
