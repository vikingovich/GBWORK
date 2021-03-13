package ru.Java3.lesson2.chat.clientside.first;

import ru.Java3.lesson2.chat.clientside.FileWork;
import ru.Java3.lesson2.chat.serverside.service.BaseAuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {

    private final Integer SERVER_PORT = 8081;
    private final String SERVER_ADDRESS = "localhost";
    private final int X = 500;
    private final int Y = 500;

    private Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    boolean isAuthorized = false;

    private JTextField msgInputField;
    private JTextArea chatArea;

    public EchoClient() {
        try {
            connection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public void connection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dis.readUTF();
                    if (messageFromServer.startsWith("/authok")) {
                        isAuthorized = true;
                        chatArea.append(messageFromServer + "\n");
                        break;
                    }
                    chatArea.append(messageFromServer + "\n");
                }

                while (isAuthorized) {
                    String messageFromServer = dis.readUTF();
                    chatArea.append(messageFromServer + "\n");
                }
            } catch (IOException ignored) {

            }
        }).start();
    }

    public void send() {
        if (msgInputField.getText() != null && !msgInputField.getText().trim().isEmpty()) {
            try {
                dos.writeUTF(msgInputField.getText());
                if (msgInputField.getText().equals("/end")) {
                    isAuthorized = false;
                    closeConnection();
                }
                msgInputField.setText("");
            } catch (IOException ignored) {
            }
        }
    }

    private void closeConnection() {
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ignored) {
        }
    }




    public void prepareGUI() {

        setBounds(X, Y, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        JButton popUpButton = new JButton("История чата");
        bottomPanel.add(popUpButton, BorderLayout.AFTER_LAST_LINE);

        popUpButton.addActionListener(e -> {

            FileWork work = new FileWork("first.txt");

            String array;
            array = (String.valueOf(work.fileReader()));

            String array2;
            array2 = array.replaceAll("\\s", "");
            array2 = array2.trim().replace(',', '\n');
            array2 = array2.replace(']', ' ');
            array2 = array2.replace('[', ' ');

            JFrame frame1 = new JFrame();
            JTextArea jTextArea = new JTextArea();
            jTextArea.setFont(new Font("Verdana", 2, 20));
            jTextArea.setLineWrap(true);
            jTextArea.setEditable(false);
            frame1.setTitle("История ордеров");
            frame1.add(new JScrollPane(jTextArea));
            frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame1.setBounds((X + X),500,500,500);
            jTextArea.setText(array2);
            frame1.setVisible(true);


        });

        btnSendMsg.addActionListener(e -> {
            send();
        });

        msgInputField.addActionListener(e -> {
            send();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (!chatArea.getText().isEmpty()) {
                    Object[] options = {"Да", "Нет!"};
                    int n = JOptionPane
                            .showOptionDialog(event.getWindow(), "Сохранить или Нет?",
                                    "Выйти", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null, options,
                                    options[0]);
                    if (n == 0) {
                        event.getWindow().setVisible(false);
                        FileWork sendToFile = new FileWork("first.txt");
                        System.out.println(chatArea.getText());
                        sendToFile.fileWriter(chatArea.getText());
                        System.exit(0);
                    }
                    if (n == 1) {
                        System.exit(1);
                    }

                }else {
                    System.exit(0);
                }
            }

        });
        setVisible(true);
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EchoClient();
        });
    }
}