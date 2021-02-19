package ru.Java2.lesson7.serverside.service;

import ru.Java2.lesson7.serverside.interfaces.AuthService;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private List<Entry> entryList;

    public BaseAuthService() {
        entryList = new ArrayList<>();
        entryList.add(new Entry("Igor", "12345", "first"));
        entryList.add(new Entry("Vova", "54321", "second"));
        entryList.add(new Entry("Dima", "11111", "threes"));
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
        for (Entry e : entryList) {
            if (e.login.equals(login) && e.password.equals(password)) {
                return e.nick;
            }
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
