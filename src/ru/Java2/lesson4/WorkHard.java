package ru.Java2.lesson4;

import ru.Java2.lesson4.interfaces.*;

import java.util.*;

import static java.lang.System.*;

public class WorkHard {

    public static void main(String[] args) {
        //метод, который возвращает индекс первого вхождения данного целого числа в списке.
        Integer[] mass = {1, 3, 5, 9, 4, 8, 12, 15, 7, 6, 5};
        SearchIndex searchIndex;
        searchIndex = (n, list) -> {
            for (int i = 0; i < list.length; i++) {
                if (list[i] == n) return i;
            }
            return -1;
        };
        out.println(searchIndex.getIndex(8, mass));
        out.println("---------------");
        //реверсируем строку
        String mystring = "java forever for me maybe";
        ReverseString reversString;
        reversString = (str) -> {
            String result = "";
            for (int i = 0; i < str.length(); i++) {
                result = str.charAt(i) + result;
            }
            return result;
        };

        out.println(reversString.reverse(mystring));
        out.println("---------------");
        //ищем максимальное число в массиве
        Integer[] max = {1, 3, 5, 2, 12, 15, 9, 1, 8, 3};
        MaxNumber myMaxNumber;
        myMaxNumber = (list) -> {
            int number = 0;
            for (int i = 0; i < list.length; i++) {
                if (number < list[i]) number = list[i];

            }
            return number;
        };
        out.println(myMaxNumber.maximum(max));
        out.println("---------------");
        // среднее значение из списка целых чисел
        ArrayList<Integer> mylist = new ArrayList<>();
        //заполняем целыми числами рандомно
        for (int i = 0; i < 20; i++) {
            mylist.add(i, (int) (Math.random() * 10));
        }
        out.println("список чисел ");
        System.out.println(mylist);
        Avarage avarageNumber;
        avarageNumber = (list) -> {
            double summ = 0;
            for (int i = 0; i < list.size(); i++) {
                summ = summ + list.get(i);

            }
            summ = summ / list.size();
            return summ;

        };
        out.println(avarageNumber.average(mylist));
        out.println("---------------");
        //возвращает список всех строк, которые начинаются с буквы «а» (нижний регистр) и имеют ровно 3 буквы.
        List<String> stringList = new ArrayList<String>();
        stringList.add("aza good");
        stringList.add("Axa good");
        stringList.add("azaz good");
        stringList.add("azA good");
        stringList.add("good aba");
        stringList.add("Abat good");

        StringOverlap checkString = (list) -> {
            for (int i = 0; i < list.size(); i++) {

                String[] word = list.get(i).split(" ");
                for (String w : word) {
                    if (w.length() == 3 && w.startsWith("a")) {
                        out.println(list.get(i));

                    }
                }
            }
            return list;
        };
        checkString.search(stringList);


    }


}

