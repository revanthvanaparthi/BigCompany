package com.example.bigcompany;

import com.example.bigcompany.model.Employee;
import com.example.bigcompany.util.CsvParser;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.jupiter.api.*;


public class CsvParserTest {

    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("test_employees", ".csv");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    private void writeLines(List<String> lines) throws IOException {
        Files.write(tempFile, lines);
    }

    @Test
    public void testValidCsvConversion() throws IOException {
        writeLines(List.of(
                "id,firstName,lastName,salary,managerId",
                "123,Joe,Doe,60000,",
                "124,Martin,Chekov,45000,123",
                "124,Thor,Odin,45000,123"
        ));
        
      Map<Integer, Employee> employees = CsvParser.parseFile(tempFile.toString());
      assertEquals(2, employees.size());
      assertEquals("Joe Doe", employees.get(123).getFullName());
      assertEquals(123, employees.get(124).getManagerId());
      assertEquals(employees.get(124), employees.get(123).getSubordinates().getFirst());
      assertEquals(true, employees.get(124).getSubordinates().isEmpty());
    }

    @Test
    public void testDuplicateEmployeeIdDataSkipped() throws IOException {
        writeLines(List.of(
                "id,firstName,lastName,salary,managerId",
                "123,Joe,Doe,60000,",
                "123,Martin,Chekov,45000," // Should be ignored
        ));

        Map<Integer, Employee> result = CsvParser.parseFile(tempFile.toString());
        assertEquals(1, result.size());
        assertEquals("Joe Doe", result.get(123).getFullName());
    }

    @Test
    public void testMultipleCeoDataSkipped() throws IOException {
        writeLines(List.of(
                "id,firstName,lastName,salary,managerId",
                "123,Joe,Doe,60000,", 		// CEO
                "124,Martin,Chekov,45000,", // Invalid 2nd CEO
                "125,Thor,Odin,45000,123"
        ));

        Map<Integer, Employee> result = CsvParser.parseFile(tempFile.toString());
        assertEquals(2, result.size());
        assertTrue(result.containsKey(123));
        assertTrue(result.containsKey(125));
        assertFalse(result.containsKey(124)); // 2nd CEO should be skipped
    }

    @Test
    public void testInValidDataSkipped() throws IOException {
        writeLines(List.of(
                "id,firstName,lastName,salary,managerId",
                "123,Joe,Doe,60000,", 		// valid
                "124,Martin,Chekov", 		// Invalid few fields
                "125,Thor,Odin,45000,123" 	// valid
        ));

        Map<Integer, Employee> result = CsvParser.parseFile(tempFile.toString());
        assertEquals(2, result.size());
        assertTrue(result.containsKey(123));
        assertTrue(result.containsKey(125));
    }
}
