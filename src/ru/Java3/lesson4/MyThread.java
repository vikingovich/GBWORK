package ru.Java3.lesson4;

public class MyThread implements Runnable {
    Thread thread;
    AnotherMethodABC abc;
    MyThread (String name, AnotherMethodABC abc1){
        thread = new Thread(this, name);
        abc = abc1;
        thread.start();
    }

    @Override
    public void run() {
        if (thread.getName().compareTo("A") == 0){
            for (int i = 0; i < 5; i++) {
                abc.methodA(true);
                abc.methodA(false);

            }
        }else if (thread.getName().compareTo("B") == 0){
            for (int i = 0; i < 5; i++) {
                abc.methodB(true);
                abc.methodB(false);

            }
        }else if (thread.getName().compareTo("C") == 0){
            for (int i = 0; i < 5; i++) {
                abc.methodC(true);
                abc.methodC(false);

            }
        }
    }
}
