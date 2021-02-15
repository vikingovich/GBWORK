package ru.Java2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Chat extends JFrame {

    private JTextField msgTextField;
    private JTextArea textArea;
    private final Integer SERVER_PORT = 8082;
    private final String SERVER_HOST = "localhost";
    private Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public Chat() {
        try {
            connection();
        } catch (IOException e){
            e.printStackTrace();
        }

        Gui();
    }

    public void connection() throws IOException {
        socket = new Socket(SERVER_HOST, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {

            try {
                while (true) {
                    String message = dis.readUTF();
                    if (message.equalsIgnoreCase("stop")) {
                        this.dispose();
                        break;
                    }
                    textArea.append(message + "\n");
                    dos.writeUTF(message);
                    System.out.println(message);


                }
            } catch (IOException ignored) {

            }


        }).start();
    }

    public void send() {


        if (msgTextField.getText() != null && !msgTextField.getText().trim().isEmpty()){
            try {
                dos.writeUTF(msgTextField.getText());
                textArea.append(msgTextField.getText() + "\n");
                msgTextField.setText("");
            } catch (IOException ignored){

            }
        }
    }
    public void closeConnection() {
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Gui() {

        setBounds(500, 500, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBackground(Color.LIGHT_GRAY);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        JPanel jPanel = new JPanel(new BorderLayout());
        JButton jButton = new JButton("Send");
        jPanel.add(jButton, BorderLayout.EAST);
        msgTextField = new JTextField();
        add(jPanel, BorderLayout.SOUTH);
        jPanel.add(msgTextField, BorderLayout.CENTER);

        jButton.addActionListener((e) -> {
            send();

        });
        msgTextField.addActionListener(e -> {
            send();

        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    dos.writeUTF("клиент закончил контакт");
                    closeConnection();
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Chat();
        });

    }
}
