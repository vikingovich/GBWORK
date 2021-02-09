package ru.Java2.lesson2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        у меня возникла одна проблема, которую я так и не понял как решить
//         в частности я решил использовать класс Array  для создания строковых массивов но вот интересный момент
//         что после того как я инициализировал  Array[][] arr = new Array[4][4];
//        не смог сделать вот так arr[0][0] = "1";
//        мне выдает все время ошибку "ava: incompatible types: java.lang.String cannot
//        be converted to ru.Java2.lesson2.Array"
//        при этом если у меня объекты String то в таком же формате все отлично
//        буду признателен если объясните


        String[][] str = new String[4][4];
        //заполняю массив числами
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[0].length; j++) {
                str[i][j] = (i + j) + "";
            }
        }
        //заменил один элемент массива символом чтобы сработало исключение
        str[2][2] = "$";

        try {

            checkArray(str);
        } catch (MyArraySizeException e) {
            //System.out.println(e.getMessage());
            //e.printStackTrace();
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            //e.printStackTrace();
            e.printStackTrace();

        }

    }

    public static void checkArray(String[][] array) throws MyArraySizeException, MyArrayDataException {

        if ((array.length == 4) & (array[0].length == 4)) {
            System.out.println("Отлично, наш массив имеет правильный размер [4][4]");
        } else {
            System.out.println("массив имеет отличный от [4][4] размер");

        }

        //проверка на символы и подсчет суммы
        Integer summ = 0;
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].matches("-?\\d+(\\.\\d+)?")) {
                    summ = summ + Integer.valueOf(array[i][j]);
                    index++;
                }else {
                    System.out.println("в массиве в ячейке " + index + " лежит символ " + array[i][j] );
                    break;
                }
            }
        }

    }
}
