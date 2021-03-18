package ru.Java3.lesson4;

public class CheckAnotherMethod {
    public static void main(String[] args) {
        AnotherMethodABC abc = new AnotherMethodABC();
        MyThread t1 = new MyThread("A", abc);
        MyThread t2 = new MyThread("B", abc);
        MyThread t3 = new MyThread("C", abc);

        try{
            t1.thread.join();
            t2.thread.join();
            t3.thread.join();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
