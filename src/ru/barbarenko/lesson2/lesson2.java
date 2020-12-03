package ru.barbarenko.lesson2;

public class lesson2 {

    public static void main(String[] args)
    {
        int[] arr1 = {2, 2, 3, 6, 1};

    changeMass();
    System.out.printf("\n");
    System.out.printf("---------------");
    System.out.printf("\n");
    System.out.printf("MASSIVE CHANGES");
    System.out.printf("\n");
    addItemsMass();
    System.out.printf("\n");
    System.out.printf("---------------");
    System.out.printf("\n");
    changeSomeItemsMass();
    System.out.printf("---------------");
    System.out.printf("\n");
    massDiagonal();
    System.out.printf("\n");
    System.out.printf("---------------");
    System.out.printf("\n");
    massMaxAndMin();
    System.out.printf("\n");
    System.out.printf("---------------");
    System.out.printf("\n");
    massCompare(arr1);

    }
        static void changeMass() //возможно нужно было буленовские операторы использовать , но сделал так
        {
            int[] arr = {1, 1, 1, 0 , 0 , 0 , 1, 0, 1, 0 ,1};
            System.out.printf("MASSIVE BEFORE");
            System.out.printf("\n");
            for (int i = 0; i < arr.length; i++)
            {
                System.out.print(arr[i] + " ");
            }
            System.out.printf("\n");
            System.out.printf("MASSIVE AFTER");
            System.out.printf("\n");
            for(int i = 0; i < arr.length; i++)
            {
                if(arr[i] == 1)
                {
                        System.out.print(0 + " ");
                }else
                    {
                        System.out.printf(1 + " ");
                    }
            }
        }
        static void addItemsMass()
        {
            int[] arr = new int[8];
            int a = 0;
            for(int i = 0; i <arr.length; i++)
            {
                a = a + 3;
                arr[i] = a;

            }
            for(int j = 0; j < arr.length; j++)

            System.out.printf(arr[j] + " ");
        }
        static void changeSomeItemsMass()
        {
            int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            int a = 0;
            System.out.printf("MASSIVE BEFORE");
            System.out.printf("\n");
            for (int i = 0; i < arr.length; i++)
            {
                System.out.print(arr[i] + " ");
            }
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < 6)
                {
                    a = arr[j] * 2;
                    arr[j] = a;
                }
            }
            System.out.printf("\n");
            System.out.printf("MASSIVE AFTER CHANGES");
            System.out.printf("\n");
            for (int k = 0; k < arr.length; k++)
            {
                System.out.print(arr[k] + " ");
            }
        }
        static void massDiagonal() {
            int[][] arr = new int[8][8];
            System.out.println("MASSIVE BEFORE");
            for (int i = 0; i < arr.length; i++) {
                System.out.println();
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = 0;
                    System.out.print(arr[i][j] + " ");
                }

            }
            int s = 0;
            for (int k = 0; k < arr.length; k++) {
                arr[k][s] = 1;
                s++;
            }
            System.out.println("\n ");
            System.out.println("MASSIVE AFTER");
            for (int i = 0; i < arr.length; i++) {
                System.out.println();
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
            }
        }
        static void massMaxAndMin()
        {
            int[] arr = {1, 5, 3, 2, 11, 19, 5, 2, 4, 21, 9, -1};
            int min, max;
            min = arr[0];
            max = arr[0];
            for(int i = 0; i < arr.length; i++)
            {
                if(min > arr[i])
                {
                    min = arr[i];
                }
                if(max < arr[i])
                {
                    max = arr[i];
                }
            }
            System.out.println("Минимальное число в массиве = " + min);
            System.out.println("Максимальное число в массиве = " + max);

        }
        static void massCompare(int arr[]) // не смотрел в общей группе, не использовал каких то специальных готовых математических функций
                                    // сделал как это вижу, и как можно решить имея те знания что есть
        {

            int s = 0;
            int p = 0;
            for (int i = 0; i < arr.length; i++) // вначале собираем сумму всех элементов массива
            {
                s = s + arr[i];
            }
            if (s % 2 == 0)        //если число не четное то в таком массиве не может быть места где левая и права часть равнялись бы
            {
                for (int j = 0; j < arr.length; j++) {
                    p = p + arr[j];
                    if (p < s / 2) {
                        continue;
                    }
                    if (p == s / 2) {
                        System.out.println("В массиве есть место где левая и правая часть равны");
                        break;
                    } else {
                        System.out.println("В этом массиве нет места где левая и правая части равны");
                        break;
                    }

                }

            } else
                {
                System.out.println("В этом массиве нет места где левая и правая части равны");
                }
        }
        static void massDisplasement()
        {
            int[] arr = {1, 2, 3, 4, 5, 6};
            int n;
            n = 1;
            for(int i = 0; i < arr.length; i++)
            {

            }
        }
    }

