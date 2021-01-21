package ru.barbarenko.lesson7;

import java.util.Scanner;

public class Plate {
    private int food;

    public Plate() {
        this.food = pressPlate();
    }

    public boolean decreaseFood(int n) {
        if (food > n) {
            food = food - n;
            return true;
        } else {
            System.out.println("в миске не хватает " + (n - food) + " единиц еды");
            return false;
        }
    }

    public void info() {
        System.out.println("В тарелке находиться " + food + " единиц еды");
    }

    public int pressPlate() {
        System.out.println("введите колво еды в тарелке");
        Scanner scanner = new Scanner(System.in);
        int summ = scanner.nextInt();
        return summ;
    }
}
