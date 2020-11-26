package ru.barbarenko.lesson1;

public class lesson1 {

    public static void main(String[] args) {
        double a, b, c, d;
        int e, f, j, i, y;
        String s;
        a = 3;
        b = 3;
        c = 4;
        d = 7;
        e = 8;
        f = 9;
        j = 12;
        i = -5;
        s = "Валера";
        y = 800;


        double result = formula(a, b, c, d);
        System.out.println("решение задачи = " + result);
        interval(e, f);
        chislo(j);
        chisloOTR(i);
        stringMeth(s);
        leapYear(y);
    }
        static double formula(double a,double b, double c,double d)
        {

            double s;
            s = (a * (b + (c / d)));
            return s;

        }

        static void interval(int a, int b)
        {
            int s;
            s = a + b;
            if(s >= 10 && s <= 20)
            {
                System.out.println("Число " + s + " находиться в интервале от 10 до 20");

            } else
                {
                System.out.println("Число " + s + " не нахогдиться в интервале от 10 до 20");
            }

        }
        static void chislo(int a)
        {
            if(a >= 0)
            {
                   System.out.println("Число " + a + " положительное");
            }else
                {
                   System.out.println("Число " + a + " отрицательное");
                }
           }
        static void chisloOTR(int a)
        {
            if(a < 0)
            {
                System.out.println("Число " + a + " Отрицательное");

            }
        }
        static void stringMeth(String s)
        {
                System.out.println("Привет " + s);
        }
        static void leapYear(int a)
        {
            int b;
            b = a % 4;
            if(b == 0)
            {
                int c;
                c = a % 100;
                if(c == 0)
                {
                    int d;
                    d = a % 400;
                    if(d == 0)
                    {
                        System.out.println(a + " Является високосным годом");
                    }else
                        {
                            System.out.println(a + " Не Является високосным годом");
                        }
                }else
                    {
                        System.out.println(a + " Является високосным годом");
                    }
            }else
                {
                    System.out.println(a + " Является обычным годом");
                }
    }
}

