package ru.barbarenko.testing;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       Dancer electric = new Electric("vita", 44);
       Dancer hard = new Hard("petrusia", 22);
       Dancer dancer = new Dancer() {
           @Override
           public void voice() {
               System.out.println("super");
           }

           @Override
           public void che() {
               System.out.println("Puper");
           }
       };
      List<Dancer> dance = Arrays.asList(dancer,electric, hard);
       for (Dancer d : dance){
           d.voice();
           d.che();
        }
    }
}
