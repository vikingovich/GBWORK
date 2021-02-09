package ru.Java2.lesson5;

public class MassWork {

    static final int size = 10000000;
    static final int h = size / 2;


    public static void main(String[] args) {

        float[] arr = new float[size];


        massOnes(arr);
        System.out.println("-------------------");
        massThread(arr);


    }

    static void massOnes(float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long time1 = System.currentTimeMillis();
        System.out.println("время заполнения массива единицами - " + time1);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));

        }
        long time2 = System.currentTimeMillis();
        System.out.println("время заполнения массива по формуле - " + time2);
        System.out.println("раздница в скорости заполнения массива едицами и по форумле - " + (time2 - time1));
    }

    static void massThread(float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long time1 = System.currentTimeMillis();
        System.out.println("время заполнения массива единицами - " + time1);

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));

            }
            long time2 = System.currentTimeMillis();
            System.out.println("время работы первого потока - " +time2 );
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));

            }
            long time3 = System.currentTimeMillis();
            System.out.println("время работы второго потока - " + time3);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("раздница в скорости заполнения массива единицами и в потоке по формуле - " + (System.currentTimeMillis() - time1));

    }


}
