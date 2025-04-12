package com.example.bigcompany;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bigcompany.model.Employee;
import com.example.bigcompany.service.EmployeeAnalyzer;
import com.example.bigcompany.util.CsvParser;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class BigcompanyApplication {

	public static void main(String[] args) throws IOException {
		
		// Currently taken sample csv for reference 
		String path = "src/main/resources/employees.csv"; 
        
        // parser to convert csv to employee list
		Map<Integer, Employee> employees = CsvParser.parseFile(path);
		
		// Currently given data for max long reports and managers salary percentage below but we can change
        int maxManagersFromCeo = 4;
        double minPercentage = 20, maxPercentage = 50;
		
		// prints all the details of Max/Min Salary of Managers and Employee Long Report to CEO
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
        analyzer.analyze(employees,maxManagersFromCeo,minPercentage,maxPercentage);
	}

}
