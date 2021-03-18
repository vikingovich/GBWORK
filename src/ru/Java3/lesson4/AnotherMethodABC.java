package ru.Java3.lesson4;

public class AnotherMethodABC {
    String currentLatter;

    synchronized void methodA(boolean running) {
        if (!running) {
            currentLatter = "A";
            notifyAll();
            return;
        }
        System.out.print(" A ");
        currentLatter = "A";
        notifyAll();
        try {
            while (!currentLatter.equals("C") )
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void methodB(boolean running) {
        if (!running) {
            currentLatter = "B";
            notifyAll();
            return;
        }
        System.out.print(" B ");
        currentLatter = "B";
        notifyAll();
        try {
            while (!currentLatter.equals("A") )
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void methodC(boolean running) {
        if (!running) {
            currentLatter = "C";
            notifyAll();
            return;
        }
        System.out.print(" C ");
        currentLatter = "C";
        notifyAll();
        try {
            while (!currentLatter.equals("B") )
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

