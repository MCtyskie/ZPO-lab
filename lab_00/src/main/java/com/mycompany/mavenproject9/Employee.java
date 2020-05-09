package com.mycompany.mavenproject9;

public class Employee {
    private int id;
    private String name;
    private String email;
    private float salary;
    
    //constructor of Employee class
    public Employee(int id, String name, String email, float salary){
        this.id=id;
        this.name=name;
        this.email=email;
        this.salary=salary;
    }
    
    
    //sets or gets employee's id
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    
    //sets or gets employee's name
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    
    //sets or gets employee's email
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    
    //sets or gets employees salary
    public void setSalary(float salary){
        this.salary=salary;
    }
    public float getSalary(){
        return salary;
    }
    
}
