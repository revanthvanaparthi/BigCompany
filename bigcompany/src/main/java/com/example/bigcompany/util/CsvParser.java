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
                
                // initialization
                int id;
                String firstName ,lastName;
                double salary = 0;
                Integer managerId = null;
                try {
                	id = Integer.parseInt(parts[0].trim());
                    firstName = parts[1].trim();
                    lastName = parts[2].trim();
                    salary = Double.parseDouble(parts[3].trim());
                    managerId = parts.length > 4 && !parts[4].trim().isEmpty()
                            ? Integer.parseInt(parts[4].trim()) : null;
                    

                    // Validations if the record is valid or not
                    if(salary < 0 || id < 0 || (firstName + lastName).equals("") || (managerId != null && managerId < 0)) {
                    	throw new Exception();  // salary will be in positive if not skipping the record as data is invalid
                    }
                    
                } catch (Exception e) {
                	System.out.printf("Record doesnt have valid data %s%n",line);
                	continue;
                }
                
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
