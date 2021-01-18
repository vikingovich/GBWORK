package ru.barbarenko.lesson6;

public class Dog extends Animals {
    public static final int defDogMaxRun = 600;
    public static final int defDogMaxJump = 80;
    public static final int defDogMaxSwim = 10;

    public Dog(int run, int jump, int swim) {
        super(run, jump, swim);
    }

    public Dog() {
        this(defDogMaxRun, defDogMaxJump, defDogMaxSwim);
    }

    public boolean run(int run) {
        if (maxRun > run) {
            System.out.println("собака пробежала дистанцию = " + run + " , а максимально возможная дистанция =" + maxRun);
            return true;
        } else {
            System.out.println("собака не смогла пробежать дистанцию " + run + "потому что максимально возможная " + maxRun);
            return false;
        }
    }
    public boolean jump(int jump) {
        if (maxJump > jump) {
            System.out.println("собака пробежала дистанцию = " + jump + " а максимально возможная дистанция =" + maxJump);
            return true;
        } else {
            System.out.println("собака не смогла пробежать дистанцию " + jump + " потому что максимально возможная " + maxJump);
            return false;
        }
    }
    public boolean swim(int swim) {
        if (maxSwim > swim) {
            System.out.println("собка пробежала дистанцию = " + swim + " а максимально возможная дистанция =" + maxSwim);
            return true;
        } else {
            System.out.println("собака не смогла пробежать дистанцию " + swim + " потому что максимально возможная " + maxSwim);
            return false;
        }
    }
}