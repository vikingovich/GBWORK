package ru.barbarenko.lesson7;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Locale;
import java.util.Scanner;

public class StringWork {

    public static void pressString(String str) {

        System.out.println("начальная строка = " + str);
        System.out.println("------");
        int i = str.length();
        System.out.println("последний символ нашей строки = " + str.charAt(i - 1));

        boolean retVal = str.endsWith("!!!");
        System.out.println("заканчивается ли строка !!! =  " + retVal);
        boolean startVal = str.startsWith("Hello");
        System.out.println("начинается ли наша строка словом Hello = " + startVal);
        boolean checkVal = str.contains("java");
        System.out.println("содержит ли наша строка подстроку java = " + checkVal);
        int lastIndex = str.indexOf("java");
        System.out.println("позиция подстроки java в основной строке = " + (lastIndex + 1));
        System.out.println("замена символов a на o  в строке= " + str.replace('a', 'o'));
        System.out.println("преобразование строки к верхнему регистру = " + str.toUpperCase(Locale.ROOT));
        System.out.println("преобразование строки к нижнему регистру = " + str.toLowerCase(Locale.ROOT));
        System.out.println("вырезаем java из строки" + str.substring(6, 10));
        //возможно не правильно понял, и нужно было вернуть строку без Java . но в любом случае есть сложность
        // c тем как определить конец подстроки, indexof  или lastindexof указывают на один и тот же элемент

    }

    public static void main(String[] args) {
        pressString("Hello java world!!!");
    }

}
