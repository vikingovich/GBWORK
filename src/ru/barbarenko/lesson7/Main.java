package ru.barbarenko.lesson7;

public class Main {
    public static void main(String[] args) {

        Plate plate = new Plate();
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("валера", 16);
        cats[1] = new Cat("петя ", 9);
        cats[2] = new Cat("жорик", 18);
        cats[3] = new Cat("пушок", 45);
        cats[4] = new Cat("гребешок", 34);
        allCats(cats);
        System.out.println("--------");
        plate.info();
        System.out.println("--------");

        for (int i = 0; i < cats.length; i++) {
            //System.out.println(cats[i].eat(plate));;
            cats[i].eat(plate);
            System.out.println(cats[i]);
            plate.info();
            System.out.println("----");
        }
        System.out.println("--------");
        allCats(cats);


    }

    public static void allCats(Cat[] cats) {
        for (Cat cat : cats) {
            System.out.println("--------");
            System.out.println(cat);

        }

    }

}
