package ru.Java2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    public static void main(String[] args) {
        HashMap<Integer, String> phoneBook = new HashMap<>();
        phoneBook.put(1232323, "БОНДАРЧУК");
        phoneBook.put(1233434, "ФИЛОНОВ");
        phoneBook.put(1234545, "КУПАЛЬСКИ");
        phoneBook.put(1235656, "ЕФРЕМОВ");
        phoneBook.put(1236767, "ИВАНОВ");
        phoneBook.put(1237878, "ФЕДОРОВ");
        phoneBook.put(1238989, "БОНДАРЧУК");
        phoneBook.put(1239090, "БОРН");
        System.out.println("список до добавления записей");
        for (HashMap.Entry<Integer, String> o : phoneBook.entrySet()) {
            System.out.println(o.getKey() + ": " + o.getValue());
        }
        System.out.println("--------------");
        System.out.println("список после добавления записей");

        add(55654645,"БОНДАРЧУК", phoneBook);
        add(60000000,"ИГНАТЬЕВ", phoneBook);
        add(99999999,"ПУПКИН", phoneBook);
        for (HashMap.Entry<Integer, String> o : phoneBook.entrySet()) {
            System.out.println(o.getKey() + ": " + o.getValue());
        }
        get(phoneBook);
    }

    static void add(Integer phone, String lastname, HashMap<Integer, String> addbook) {

        addbook.put(phone, lastname);



    }

    static void get(HashMap<Integer, String> getbook) {
        for (HashMap.Entry<Integer, String> o : getbook.entrySet()) {
            if (o.getValue().equals("БОНДАРЧУК")) {

                System.out.print(o.getKey() + ": ");
            }
        }
        System.out.printf("БОНДАРЧУК");
    }
}

