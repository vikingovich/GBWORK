package ru.Java2.lesson7.serverside.interfaces;

public interface AuthService {

    void start();
    void stop();
    String getNickByLoginAndPassword(String login, String password);
}
