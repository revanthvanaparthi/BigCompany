package com.example.bigcompany.util;

import java.io.*;
import java.util.*;

import com.example.bigcompany.model.Employee;

public class CsvParser {
	
    public static Map<Integer, Employee> parseFile(String path) throws IOException {
        Map<Integer, Employee> employees = convertCsvToEmployees(path);
        return employees;
    }
    
    // converts csv to employee object list 
    private static Map<Integer, Employee> convertCsvToEmployees(String path) throws IOException {
    	
        Map<Integer, Employee> employees = new HashMap<>();
        boolean hasCeo = false;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Validations if the record does not have all the fields
                if(parts.length < 4) {
                	System.out.printf("Record doesnt have complete data %s%n",line);
                	continue;
                }
                
                // initialization
                int id = Integer.parseInt(parts[0].trim());
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());
                Integer managerId = parts.length > 4 && !parts[4].trim().isEmpty()
                        ? Integer.parseInt(parts[4].trim()) : null;
                
                // Company should have only one CEO
                if(hasCeo && managerId == null) {
                	System.out.printf("Record is not valid %s as CEO is already present%n",line);
                	continue;
                }
                if(managerId == null) {
                	hasCeo = true;
                }
                
                // Duplicate Id Validations
                if(employees.isEmpty() || employees.get(id) == null) {
                    Employee emp = new Employee(id, firstName, lastName, salary, managerId);
                    employees.put(id, emp);
                } else {
                	 System.out.printf("Record with Id: %d with Full Name: %s %s is ignored as data already exists%n",id, firstName, lastName);
                }
            }
        }
        
        linkEmployeesToManager(employees);
        
        return employees;
    	
    }
    
    // Link employees to managers
    private static void linkEmployeesToManager(Map<Integer, Employee> employees) {
    	for (Employee e : employees.values()) {
            if (e.getManagerId() != null) {
                Employee manager = employees.get(e.getManagerId());
                if (manager != null) {
                    manager.addSubordinate(e);
                }
            }
        }
    }
}
