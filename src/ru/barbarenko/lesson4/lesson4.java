
package ru.barbarenko.lesson4;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

class MyWindow  extends JFrame {
    public MyWindow(String name) {
        setTitle(name);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setBackground(Color.gray);
        JButton[][] jbs = new JButton[5][5];
        setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for(int j =0; j < 5; j++) {
                jbs[i][j]= new JButton("");
                add(jbs[i][j]);
            }
        }

    }


}


public class lesson4 {

    private static final int SIZE = 5;
    private static final char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    public static final int WIN = 4; //условие победы, колво символов подряд
    public static final int BEFORE_WIN = 2;//колво подряд ходов человека

    public static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) {
        MyWindow mywindow = new MyWindow("game");
        initializeGame();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkEndGame(DOT_X)) {
                break;
            }

            computerTurn();
            printMap();
            if (checkEndGame(DOT_O)) {
                break;
            }
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (checkWin(symbol)) {
            System.out.println(isHumanTurn(symbol) ? "Человек победил!" : "Машина победила!");
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (char[] row : map) {
            for (char cellValue : row) {
                if (cellValue == DOT_EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkWin(char symbol) {

        int check;
        //проверка строк
        for (int columns = 0; columns < SIZE; columns++) {
            check = 0;
            for (int rows = 0; rows < SIZE; rows++) {
                if (map[columns][rows] == symbol) check++;
                if (check == WIN) return true;
            }
        }
        //проверка столбцов
        for (int columns = 0; columns < SIZE; columns++) {
            check = 0;
            for (int rows = 0; rows < SIZE; rows++) {
                if (map[rows][columns] == symbol) check++;
                if (check == WIN) return true;

            }
        }
        //проверка по диагонали с лево направо
        int s = 0;
        check = 0;
        for (int k = 0; k < SIZE; k++) {
            if (map[k][s] == symbol) {
                check++;
            } else {
                check = 0;
            }
            if (check == WIN) return true;
            s++;
        }//проверка по диагонали с права на лево
        s = SIZE - 1;
        check = 0;
        for (int k = 0; k < SIZE; k++) {
            if (map[k][s] == symbol) {
                check++;
            } else {
                check = 0;
            }
            if (check == WIN) return true;
            s--;
        }
        return false;

    }

    private static void humanTurn() {
        int rowIndex = -1;
        int colIndex = -1;

        do {
            System.out.print("Введите номер строки: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            rowIndex = SCANNER.nextInt() - 1;

            System.out.print("Введите номер столбца: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            colIndex = SCANNER.nextInt() - 1;
        } while (!isCellValid(rowIndex, colIndex, DOT_X));

        map[rowIndex][colIndex] = DOT_X;
    }

    private static void computerTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        if (computerAnalize()) {
            return;
        } else {
            Random rand = new Random();
            do {
                rowIndex = rand.nextInt(SIZE);
                colIndex = rand.nextInt(SIZE);
            } while (!isCellValid(rowIndex, colIndex, DOT_O));

            map[rowIndex][colIndex] = DOT_O;
        }
    }

    public static boolean computerAnalize() {
        //проверка вхождения по строкам
        for (int columns = 0; columns < SIZE; columns++) {
            for (int rows = 0; rows < SIZE; rows++) {

                if ((rows - 1) >= 0 & (rows + 1) < SIZE) {
                    if ((map[columns][rows] == DOT_X) & (map[columns][rows + 1] == DOT_X)) {
                        if (map[columns][rows - 1] == DOT_EMPTY) {
                            map[columns][rows - 1] = DOT_O;
                            return true;
                        }
                    }
                }
                if ((rows + 2) < SIZE ) {
                    if (map[columns][rows + 2] == DOT_EMPTY) {
                        map[columns][rows + 2] = DOT_O;
                        return true;
                    }
                }

            }

        }


        //проверка столбцов
        for (int columns = 0; columns < SIZE; columns++) {
            int userIndex = 0;
            for (int rows = 0; rows < SIZE; rows++) {
                if (map[rows][columns] == DOT_X) userIndex++;
                if (userIndex >= BEFORE_WIN) {
                    if (map[0][columns] == DOT_EMPTY) {
                        map[0][columns] = DOT_O;
                        return true;
                    }
                    if (map[1][columns] == DOT_EMPTY) {
                        map[1][columns] = DOT_O;
                        return true;
                    }
                    if (map[2][columns] == DOT_EMPTY) {
                        map[2][columns] = DOT_O;
                        return true;
                    }
                    if (map[3][columns] == DOT_EMPTY) {
                        map[3][columns] = DOT_O;
                        return true;
                    }
                    if (map[4][columns] == DOT_EMPTY) {
                        map[4][columns] = DOT_O;
                        return true;
                    }
                }
            }
        }


        int s = 0;
        int j = 0;
        int userIndex = 0;
        for (int k = 0; k < SIZE; k++) {
            if (map[k][s] == DOT_X) userIndex++;
            if (userIndex >= BEFORE_WIN) {
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][j] == DOT_EMPTY) {
                        map[i][j] = DOT_O;
                        return true;
                    }
                    j++;
                }
            }
            s++;
        }
        //проверка по диагонали с права на лево
        s = SIZE - 1;
        j = SIZE - 1;
        userIndex = 0;
        for (int k = 0; k < SIZE; k++) {
            if (map[k][s] == DOT_X) userIndex++;
            if (userIndex >= BEFORE_WIN) {
                for (int i = 0; i < SIZE; i++) {
                    if (map[i][j] == DOT_EMPTY) {
                        map[i][j] = DOT_O;
                        return true;
                    }
                    j--;
                }
            }
            s--;
        }
        return false;
    }


    private static boolean isCellValid(int rowIndex, int colIndex, char symbol) {

        if (!isArrayIndexValid(rowIndex) || !isArrayIndexValid(colIndex)) {
            System.out.println("Индексы строки и колонки должны быть в диапазоне от 1 до " + SIZE);
            return false;
        }
        if (map[rowIndex][colIndex] != DOT_EMPTY) {
            if (isHumanTurn(symbol)) {
                System.out.println("Данная ячейка уже занята!");
            }
            return false;
        }

        return true;
    }

    private static boolean isHumanTurn(char symbol) {
        return symbol == DOT_X;
    }

    private static boolean isArrayIndexValid(int index) {
        return index >= 0 && index < SIZE;
    }

    private static void printMap() {
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            System.out.print((rowIndex + 1) + " ");

            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                System.out.print(map[rowIndex][colIndex] + " ");
            }

            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                map[rowIndex][colIndex] = DOT_EMPTY;
            }
        }
    }

}


