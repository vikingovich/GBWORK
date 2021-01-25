package ru.Java2.lesson1;

public class RaceTrack implements Trials {
    private int distance;

    public RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean checkRun(int distance) {
        if (this.distance < distance) {
            System.out.println("СМОГ ПРОБЕЖАТЬ ДИСТАНЦИЮ " + this.distance);
            return true;
        } else {
            System.out.println("НЕ СМОГ ПРОБЕЖАТЬ ДИСТАНЦИЮ " + this.distance);
            return false;
        }

    }

    @Override
    public String toString() {
        return "RaceTrack{" +
                "distance=" + distance +
                '}';
    }
}


