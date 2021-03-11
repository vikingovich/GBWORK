package ru.Java3.lesson2.chat.serverside.interfaces;

public interface AuthService {

    void start();
    void stop();
    String getNickByLoginAndPassword(String login, String password);
}
