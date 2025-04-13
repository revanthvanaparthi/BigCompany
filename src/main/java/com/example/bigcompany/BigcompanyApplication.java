package com.example.bigcompany;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bigcompany.model.Constants;
import com.example.bigcompany.model.Employee;
import com.example.bigcompany.service.EmployeeAnalyzer;
import com.example.bigcompany.util.CsvParser;

import java.util.Map;


@SpringBootApplication
public class BigcompanyApplication {

	public static void main(String[] args) {
		
		try {
			
			// parser to convert csv to employee list
			Map<Integer, Employee> employees = CsvParser.parseFile(Constants.FILE_PATH);
			
			// prints all the details of Max/Min Salary of Managers and Employee Long Report to CEO
	        EmployeeAnalyzer analyzer = new EmployeeAnalyzer();
	        analyzer.analyze(employees);
			
		} catch (Exception e) {
			System.out.println("BigcompanyApplication class Throws Exception: " + e.getMessage());
		}
	}

}
