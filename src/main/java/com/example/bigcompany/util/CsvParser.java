package com.example.bigcompany.util;

import java.io.*;
import java.util.*;

import com.example.bigcompany.model.Employee;
import com.example.bigcompany.exception.DuplicateEmployeeException;
import com.example.bigcompany.exception.InvalidDataException;
import com.example.bigcompany.exception.MultipleCeoException;

public class CsvParser {
	
    public static Map<Integer, Employee> parseFile(String path) throws IOException {
        return convertCsvToEmployees(path);
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
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String firstName = parts[1].trim();
                    String lastName = parts[2].trim();
                    double salary = Double.parseDouble(parts[3].trim());
                    Integer managerId = parts.length > 4 && !parts[4].trim().isEmpty()
                            ? Integer.parseInt(parts[4].trim()) : null;

                    // Validate data
                    if (salary < 0 || id < 0 || (firstName + lastName).isEmpty() || (managerId != null && managerId < 0)) {
                        throw new InvalidDataException("Invalid data in line: " + line);
                    }

                    if (hasCeo && managerId == null) {
                        throw new MultipleCeoException("Multiple CEOs found. Line: " + line);
                    }

                    if (managerId == null) {
                        hasCeo = true;
                    }

                    if (employees.containsKey(id)) {
                        throw new DuplicateEmployeeException("Duplicate ID " + id + " for " + firstName + " " + lastName);
                    }

                    Employee emp = new Employee(id, firstName, lastName, salary, managerId);
                    employees.put(id, emp);

                } catch (InvalidDataException | DuplicateEmployeeException | MultipleCeoException e) {
                    System.out.println(e.getMessage()); // Can also log this
                    continue;
                } catch (Exception e) {
                    System.out.printf("Unexpected error parsing line: %s%n", line);
                    continue;
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

