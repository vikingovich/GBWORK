package ru.barbarenko.lesson5;

import com.sun.javafx.collections.FloatArraySyncer;


public class Staff {

    String name;
    String occupation;
    String email;
    String phone;
    int salary;
    int age;

    Staff(String name, String occupation, String email, String phone, int salary, int age) {

        this.name = name;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    void consol()
{

        System.out.println(" имя: " + name + " \n должность: " + occupation + "\n почта: " + email + "\n телефон: " + phone +
                " \n зарплата: " + salary + " \n возраст: " + age);
    System.out.println("-------------");

    }



    public static void main(String[] args) {

        Staff[] persArray = new Staff[5];
        persArray[0] = new Staff("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Staff("sergey petrovich", "biotech", "bio@tech.com", "3453453453", 50000, 40);
        persArray[2] = new Staff("dmitriy radon", "Engineer", "ivivan@mailbox.com", "12334433", 35000, 50);
        persArray[3] = new Staff("igor berb", "Engineer", "ivivan@mailbox.com", "564524234", 90000, 20);
        persArray[4] = new Staff("aleksy mikoin", "Engineer", "goga@ipo.com", "23423234", 100000, 29);


        for (int i = 0; i < persArray.length; i++)
        {

            if(persArray[i].getAge() >= 40)  persArray[i].consol();
        }



    }



}