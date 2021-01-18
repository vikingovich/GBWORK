package ru.barbarenko.lesson6;

public class Cat extends Animals {
    public static final int defCatMaxRun = 400;
    public static final int defCatMaxJump = 50;
    public static final int defCarMaxSwim = 0;

    public Cat(int run, int jump) {
        super(run, jump, defCarMaxSwim);
    }

    public Cat() {
        this(defCatMaxRun, defCatMaxJump);
    }

    public boolean run(int run) {
        if (maxRun > run) {
            System.out.println("Кот пробежал дистанцию = " + run + " , а максимально возможная дистанция =" + maxRun);
            return true;
        } else {
            System.out.println("Кот не смог пробежать дистанцию " + run + " потому что максимально возможная " + maxRun);
            return false;
        }
    }
    public boolean jump(int jump) {
        if (maxJump > jump) {
            System.out.println("Кот пробежал дистанцию = " + jump + " а максимально возможная дистанция =" + maxRun);
            return true;
        } else {
            System.out.println("Кот не смог пробежать дистанцию " + jump + " потому что максимально возможная " + maxRun);
            return false;
        }
    }
    public boolean swim(int swim) {
            System.out.println("коты не умеют плавать");
            return false;
        }
    }

