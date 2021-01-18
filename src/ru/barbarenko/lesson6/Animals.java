package ru.barbarenko.lesson6;

public abstract class Animals {
    protected int maxRun;
    protected int maxJump;
    protected int maxSwim;

    public Animals(int run, int jump, int swim) {
        this.maxRun = run;
        this.maxJump = jump;
        this.maxSwim = swim;
    }

    public abstract boolean run(int dist);
    public abstract boolean jump(int height);
    public abstract boolean swim(int dist);

   public void info(){
   System.out.println(this);
   }


    @Override
    public String toString() {
        return "Animals{" +
                "maxRun=" + maxRun +
                ", maxJump=" + maxJump +
                ", maxSwim=" + maxSwim +
                '}';
    }
}

