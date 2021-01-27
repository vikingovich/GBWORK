package ru.barbarenko.testing;

public class Hard implements Dancer {

    private String name;
    private int age;

    public Hard(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void che(){
        System.out.printf("возраст" + age);
    }

    public void voice(){
        System.out.println(toString() + "танцует хард рок");

    }

    @Override
    public String toString() {
        return "Hard{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
