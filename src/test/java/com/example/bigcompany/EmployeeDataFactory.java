package com.example.bigcompany;

import java.util.HashMap;
import java.util.Map;

import com.example.bigcompany.model.Employee;

public class EmployeeDataFactory {
    public static Map<Integer, Employee> buildLessEarnEmployees() {
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
    
    public static Map<Integer, Employee> buildLongReportingEmployees() {
        Employee ceo = new Employee(1, "Alice", "CEO", 150000, null);
        Employee emp1 = new Employee(2, "Bob", "M1", 120000, 1);
        Employee emp2 = new Employee(3, "Charlie", "Worker", 100000, 2);
        Employee emp3 = new Employee(4, "Bob", "M1", 120000, 3);
        Employee emp4 = new Employee(5, "Steve", "Rogers", 100000, 4);
        Employee emp5 = new Employee(6, "Brett", "Hardleaf", 100000, 5);

        Map<Integer, Employee> employees = Map.of(
                1, ceo,
                2, emp1,
                3, emp2,
                4, emp3,
                5, emp4,
                6, emp5
        );

        return employees;
    }
    
    public static Map<Integer, Employee> buildWithInReportingEmployees() {
        Employee ceo = new Employee(1, "Alice", "CEO", 150000, null);
        Employee m1 = new Employee(2, "Bob", "M1", 120000, 1);
        Employee emp = new Employee(3, "Charlie", "Worker", 100000, 2);

        Map<Integer, Employee> employees = Map.of(
                1, ceo,
                2, m1,
                3, emp
        );

        return employees;
    }
}

