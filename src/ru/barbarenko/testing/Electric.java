package ru.barbarenko.testing;

public class Electric implements Dancer {

    private String name;
    private int age;

    public Electric(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void voice(){
        System.out.println(toString() + "я люблю элктро");
    }
    public void che(){
        System.out.printf("возраст" + age);
    }
    @Override
    public String toString() {
        return "Electric{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
