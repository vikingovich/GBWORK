package ru.Java2.lesson3;

import java.sql.SQLOutput;
import java.util.*;

public class MassivParsing {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("ВИКТОР");
        arrayList.add("ДМИТРИЙ");
        arrayList.add("ИВАН");
        arrayList.add("СЕРГЕЙ");
        arrayList.add("ДАВИД");
        arrayList.add("ВИКТОР");
        arrayList.add("ИГОРЬ");
        arrayList.add("ПЕТЯ");
        arrayList.add("ДАВИД");
        arrayList.add("ДАВИД");
        arrayList.add("ВАСЯ");
        arrayList.add("ВЛАДИМИР");
        arrayList.add("СЕРГЕЙ");
        arrayList.add("ДАНИИЛ");

        System.out.println("Изначальный список");
        System.out.println(arrayList);
        massCompare(arrayList);

    }

    static void massCompare(ArrayList array) {
        HashSet<String> unics = new HashSet<>(array);
        Iterator<String> iter = unics.iterator();
        int summ = 0;
        String s;
        while (iter.hasNext()) {
            summ = 0;
            s = iter.next();
            for (int i = 0; i < array.size(); i++) {

                if (s.equals(array.get(i))) summ++;

            }
            System.out.println("Имя " + s + " встречается в списке " + summ + " раз");

        }
        System.out.println("---------");
        System.out.println("Теперь список уникальных имен");
        Iterator<String> iter2 = unics.iterator();
        while (iter2.hasNext()) {
            summ = 0;
            s = iter2.next();
            for (int i = 0; i < array.size(); i++) {

                if (s.equals(array.get(i))) summ++;

            }
            if (summ  == 1)
                System.out.println("Имя " + s + " уникальное без дублей ");

        }

    }
}




