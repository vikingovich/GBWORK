package ru.barbarenko.lesson6;

import java.util.Random;

public class Main {


    public static void main(String[] args) {


//        Cat cat1 = new Cat(100, 200);
//        Cat cat2 = new Cat();
//        Dog dog1 = new Dog(250, 200, 300);
//        Dog dog2 = new Dog();
//        System.out.println(cat1);
//        System.out.println(cat2);
//        cat2.run(40);
//        System.out.println(dog1);
//        dog1.run(200);
//        System.out.println(dog2);


        Animals[] participant = prepareParticipants();
        checkAnimal(participant);
    }
        private static Animals[] prepareParticipants(){
            return new Animals[] {
                    new Cat(),
                    new Dog(),
                    new Cat(100, 200),
                    new Dog(100, 100, 100),
                    new Dog(500, 300, 100)
            };
        }
    private static void checkAnimal (Animals[] participants){
            Random random = new Random();

            for (Animals participant: participants) {

                System.out.println(participant.run(random.nextInt(300)));
                System.out.println(participant.jump(random.nextInt(50)));
                System.out.println(participant.swim(random.nextInt(150)));
            }

        }



    }
