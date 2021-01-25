package ru.Java2.lesson1;

import ru.Java2.lesson1.Creatures.Cat;
import ru.Java2.lesson1.Creatures.Human;
import ru.Java2.lesson1.Creatures.Robot;

public class Main {

    public static void main(String[] args) {

        Trials[] trials = new Trials[4];
        trials[0] = new Wall(40);
        trials[1] = new Wall(110);
        trials[2] = new RaceTrack(50);
        trials[3] = new RaceTrack(200);

        Inter[] array = new Inter[6];
        array[0] = new Cat("барсик", 50, 100);
        array[1] = new Cat("шляпник", 101, 80);
        array[2] = new Human("димон", 50, 200);
        array[3] = new Human("валера", 240, 50);
        array[4] = new Robot("валли", 99, 50);
        array[5] = new Robot("ротор", 101, 80);


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < trials.length; j++) {

                if (trials[j] instanceof Wall) {
                    array[i].jumper((Wall) trials[j]);

                }

                if (trials[j] instanceof RaceTrack) {
                    array[i].runner((RaceTrack) trials[j]);

                }

            }

        }

    }

}
