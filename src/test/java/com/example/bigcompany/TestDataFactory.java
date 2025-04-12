package com.example.bigcompany;

import java.util.HashMap;
import java.util.Map;

import com.example.bigcompany.model.Employee;

public class TestDataFactory {
    public static Map<Integer, Employee> buildLessEarnAndLongReportEmployees() {
        Map<Integer, Employee> employees = new HashMap<>();

        Employee joe = new Employee(123, "Joe", "Doe", 60000, null);
        Employee martin = new Employee(124, "Martin", "Chekov", 45000, 123);
        Employee bob = new Employee(125, "Bob", "Ronstad", 47000, 123);
        Employee alice = new Employee(300, "Alice", "Hasacat", 50000, 124);
        Employee brett = new Employee(305, "Brett", "Hardleaf", 34000, 300);

        employees.put(joe.getId(), joe);
        employees.put(martin.getId(), martin);
        employees.put(bob.getId(), bob);
        employees.put(alice.getId(), alice);
        employees.put(brett.getId(), brett);

        joe.addSubordinate(martin);
        joe.addSubordinate(bob);
        martin.addSubordinate(alice);
        alice.addSubordinate(brett);

        return employees;
    }
    
    public static Map<Integer, Employee> buildMoreEarnEmployees() {
        Map<Integer, Employee> employees = new HashMap<>();

        Employee joe = new Employee(123, "Joe", "Doe", 110000, null);
        Employee martin = new Employee(124, "Martin", "Chekov", 50000, 123);
        Employee bob = new Employee(125, "Bob", "Ronstad", 50000, 123);

        employees.put(joe.getId(), joe);
        employees.put(martin.getId(), martin);
        employees.put(bob.getId(), bob);

        joe.addSubordinate(martin);
        joe.addSubordinate(bob);

        return employees;
    }
    
    public static Map<Integer, Employee> buildEmployeesInBand() {
        Map<Integer, Employee> employees = new HashMap<>();

        Employee joe = new Employee(123, "Joe", "Doe", 72000, null);
        Employee martin = new Employee(124, "Martin", "Chekov", 50000, 123);
        Employee bob = new Employee(125, "Bob", "Ronstad", 50000, 123);

        employees.put(joe.getId(), joe);
        employees.put(martin.getId(), martin);
        employees.put(bob.getId(), bob);

        joe.addSubordinate(martin);
        joe.addSubordinate(bob);

        return employees;
    }
}

