package com.example.bigcompany;

import org.junit.jupiter.api.*;

import com.example.bigcompany.model.Employee;
import com.example.bigcompany.service.EmployeeAnalyzer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeAnalyzerTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private EmployeeAnalyzer analyzer;


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
        analyzer = new EmployeeAnalyzer();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testManagerEarningLessThanAllowed() {
    	Map<Integer, Employee> employees = EmployeeDataFactory.buildLessEarnEmployees();
        analyzer.analyze(employees);

        String result = output.toString();
        assertTrue(result.contains("Manager Martin Chekov earns LESS than they should by 15000.00"));
        assertFalse(result.contains("Manager Joe Doe earns LESS"));
    }

    @Test
    public void testManagerEarningMoreThanAllowed() {
        Map<Integer, Employee> employees =  EmployeeDataFactory.buildMoreEarnEmployees();
        analyzer.analyze(employees);

        String result = output.toString();
        assertTrue(result.contains("Manager Joe Doe earns MORE than they should by"));
    }

    @Test
    public void testManagerEarningWithinBand() {
        Map<Integer, Employee> employees =  EmployeeDataFactory.buildEmployeesInBand();
        analyzer.analyze(employees);

        // Expecting no output
        String result = output.toString();
        assertEquals("", result);
    }

    @Test
    public void testEmployeeWithTooLongReportingLine() {
    	Map<Integer, Employee> employees =  EmployeeDataFactory.buildLongReportingEmployees();
        analyzer.analyze(employees);

        String result = output.toString();
        assertTrue(result.contains("Employee Brett Hardleaf has reporting line too long"));
    }
    

    @Test
    public void testEmployeeWithinReportingLimit() {
    	Map<Integer, Employee> employees =  EmployeeDataFactory.buildWithInReportingEmployees();
        analyzer.analyze(employees);

        // Expecting no output
        String result = output.toString();
        assertEquals("", result);
    }

    @Test
    public void testCEOReportingIsIgnored() {
        Employee ceo = new Employee(1, "Alice", "CEO", 150000, null);
        Map<Integer, Employee> employees = Map.of(1, ceo);
        analyzer.analyze(employees);


        // Expecting no output
        String result = output.toString();
        assertEquals("", result);
    }
}