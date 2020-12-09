package ru.barbarenko;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Random;
import java.util.Scanner;

public class lesson3 {

    public static void main(String[] args)
    {
    random();

    }

    static void random()
    {
        int gameOverOrNot =0;
        do {
            Random rand = new Random();
            int r = rand.nextInt(10);
            int input;
            int i = 3;
            int j = 1;
            do {
                System.out.println("Введите число от 0 до 9");
                Scanner scan = new Scanner(System.in);
                input = scan.nextInt();
            } while (input < 0 || input > 9);

               do {
                   i--;
                   if (r < input) {
                       System.out.println("Искомое число меньше " + input);
                       System.out.println("у вас осталось " + i + " попыток");
                   } else if (r > input) {
                       System.out.println("Искомое число больше " + input);
                       System.out.println("у вас осталось " + i + " попыток");
                   } else if (r == input) {
                       System.out.println(" Вы угадали число c " + j + " попытки, и оно равно " + r);
                       break;
                   }
                   j++;
                   System.out.println("Введите число");
                   Scanner scan = new Scanner(System.in);
                   input = scan.nextInt();
               }while (i >1);

            if (r != input) System.out.println("Вы не угадали число с трех попыток. Верный ответ =  " + r);
            System.out.println("\n");
            System.out.println("Если хотите поиграть еще нажмите 1 , если нет 0");
            Scanner again = new Scanner(System.in);
            gameOverOrNot = again.nextInt();

        }while (gameOverOrNot != 0);

    }

}

