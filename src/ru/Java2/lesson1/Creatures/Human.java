package ru.Java2.lesson1.Creatures;

import ru.Java2.lesson1.Inter;
import ru.Java2.lesson1.RaceTrack;
import ru.Java2.lesson1.Wall;

public class Human implements Inter {
    private String name;
    private int run;
    private int jump;
    private static final String type ="ЧЕЛОВЕК";

    public Human(String name, int run, int jump) {
        this.name = name;
        this.run = run;
        this.jump = jump;
    }
    public void runner(){
        System.out.println(type + " умеет бегать на расстояние" + run);
    }
    public void jumper(){
        System.out.println(type + " умеет прыгать на высоту " + jump);
    }
    public void runner(RaceTrack r) {
        System.out.print(type + " : " + name + " : ");
        r.checkRun(run);
        System.out.println("-----");
    }

    public void jumper(Wall w) {
        System.out.print(type + " : " + name + " : ");
        w.checkWall(jump);
        System.out.println("-----");
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", run=" + run +
                ", jump=" + jump +
                '}';
    }
}
