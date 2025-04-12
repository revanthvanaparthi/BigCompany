package com.example.bigcompany.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	
	private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private Integer managerId; // can be null
    private List<Employee> subordinates = new ArrayList<>();

    public Employee(int id, String firstName, String lastName, double salary, Integer managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }
    
    public void addSubordinate(Employee e) {
        subordinates.add(e);
    }

    // Getters
    public String getFullName(){
        return firstName + " " + lastName;
    }
    
    public int getId(){
    	return id;
    }
    
    public double getSalary(){
    	return salary;
    }
    
    public Integer getManagerId(){ 
    	return managerId; 
    }
    
    public List<Employee> getSubordinates(){
    	return subordinates; 
    }
}
