
package com.mycompany.mavenproject13;

import static com.mycompany.mavenproject13.StudentsSexEnum.*;
import java.util.ArrayList;
import java.util.List;


public class Student {
    
    private String name;
    private Enum sex;
    private int age;
    private List<String> studentsNames=new ArrayList();
    
    public Student(){
        this.name="mic";
        this.sex=MALE;
        this.age=21;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum getSex() {
        System.out.println(sex);
        return sex;
    }

    public void setSex(Enum sex) {
        this.sex = sex;
    }

    public int getAge() {
        System.out.println(age);
        return age;
    }
//    private int getAge(int age){
//        return age;
//    }

    public void setAge(int age) {
        this.age = age;
    }
    
//    public void addNameToList(){
//        studentsNames.add(this.name);
//    }
    
    public void callMyName(){
        System.out.println("Yo, my name is: "+this.name);
    }
    private void askMyAge(){
        System.out.println("Too young, sorry. I'm: "+this.age);
    }
    
    
}
