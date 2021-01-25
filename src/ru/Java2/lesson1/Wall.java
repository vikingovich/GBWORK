package ru.Java2.lesson1;

public class Wall implements Trials {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean checkWall(int height) {
        if (this.height < height) {
            System.out.println("СМОГ ПЕРЕРЫГНУТЬ СТЕНУ ВЫСОТОЙ " + this.height);
            return true;
        } else {
            System.out.println("НЕ СМОГ ПЕРЕПРЫГНУТЬ СТЕНУ ВЫСОТОЙ " + this.height);
            return false;
        }

    }

    @Override
    public String toString() {
        return "Wall{" +
                "height=" + height +
                '}';
    }
}
