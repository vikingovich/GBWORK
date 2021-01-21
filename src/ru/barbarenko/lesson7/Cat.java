package ru.barbarenko.lesson7;

public class Cat {
    private int appetite;
    private String name;
    private boolean fullness = false; // сытость

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {//проверка хватает ли еды, если хватает то сытость кота меняется на true, аппетит становиться 0
        // и вычитается
        //соотвественно колво еды из миски. я придумал такую реализацию. решил не смотреть заранее
        //как ее решаете вы

        if (p.decreaseFood(appetite)) {
            appetite = 0;
            fullness = true;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "имя=" + name +
                ", аппетит='" + appetite + '\'' +
                ", fullness=" + fullness +
                '}';
    }
}
