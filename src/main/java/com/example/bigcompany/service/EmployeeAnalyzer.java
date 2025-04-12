package com.example.bigcompany.service;

import com.example.bigcompany.model.Employee;

import java.util.Map;

public class EmployeeAnalyzer {

    public void analyze(Map<Integer, Employee> employees, int maxManagersFromCeo, double minPercentage, double maxPercentage) {
    	getManagersOustSideBand(employees, minPercentage, maxPercentage);
        getCeoLongReporties(employees, maxManagersFromCeo);
    }
    
    // Prints all the managers earn more/less than they should, and by how much    
    private void getManagersOustSideBand(Map<Integer, Employee> employees, double minPercentage, double maxPercentage) {
        for (Employee manager : employees.values()) {
            if (!manager.getSubordinates().isEmpty()) {
                double avgSubSalary = manager.getSubordinates().stream()
                        .mapToDouble(Employee::getSalary)
                        .average().orElse(0);
                
                double minAllowed = avgSubSalary * (minPercentage/100 + 1);
                double maxAllowed = avgSubSalary * (maxPercentage/100 + 1);

                if (manager.getSalary() < minAllowed) {
                    System.out.printf("Manager %s earns LESS than they should by %.2f%n", manager.getFullName(), minAllowed - manager.getSalary());
                } else if (manager.getSalary() > maxAllowed) {
                    System.out.printf("Manager %s earns MORE than they should by %.2f%n", manager.getFullName(), manager.getSalary() - maxAllowed);
                }
            }
        }
    }
    
    // Prints all the employees who has reporting line which is too long and by how much
    private void getCeoLongReporties(Map<Integer, Employee> employees, int maxManagersFromCeo) {
    	for (Employee e : employees.values()) {
            int depth = getDepth(e, employees);
            if (depth > maxManagersFromCeo) {
                System.out.printf("Employee %s has reporting line too long: %d levels%n", e.getFullName(), depth);
            }
        }
    }

    // gets depth from CEO to Employee
    private int getDepth(Employee e, Map<Integer, Employee> employees) {
        int depth = 0;
        Integer managerId = e.getManagerId();

        while (managerId != null) {
            depth++;
            Employee manager = employees.get(managerId);
            if (manager == null) break;
            managerId = manager.getManagerId();
        }

        return depth;
    }
}

