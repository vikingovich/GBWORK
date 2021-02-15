package ru.Java2.lesson6;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class MyServer {


    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            new Thread(() -> {
                try (ServerSocket serverSocket = new ServerSocket(8082)) {
                    Socket socket;
                    String message = "";
                    System.out.println("Server start work!");
                    socket = serverSocket.accept();
                    System.out.println("Client JOIN");
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    while (true) {
                        message = dis.readUTF();

                        if (message.equalsIgnoreCase("stop")) {
                            dos.writeUTF(message);
                            System.out.println("сервер закончил работу");

                            break;
                        }
                        System.out.println(message);


                        new  Thread(() -> {
                           try   {
                            String ms = reader.readLine();

                                    if(!ms.isEmpty()) dos.writeUTF("Server:  " + ms);

                                } catch(IOException e){
                                    e.printStackTrace();
                                }

                       }).start();
                        //String ms = reader.readLine();
                        //dos.writeUTF("Server:  " + message);


                    }


                } catch (Exception e){
                    e.printStackTrace();
                }
            }).start();


    }
}
