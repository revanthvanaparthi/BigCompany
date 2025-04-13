package com.example.bigcompany;

import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.example.bigcompany.model.Employee;
import com.example.bigcompany.service.EmployeeAnalyzer;
import com.example.bigcompany.util.CsvParser;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BigcompanyApplicationTests {

	@Test
	void contextLoads() {
		// Prepare fake data
        Map<Integer, Employee> fakeEmployeeMap = new HashMap<>();

        // Mock static method CsvParser.parseFile()
        try (MockedStatic<CsvParser> csvParserMock = mockStatic(CsvParser.class)) {

            csvParserMock.when(() -> CsvParser.parseFile(anyString()))
                         .thenReturn(fakeEmployeeMap);

            // Spy the EmployeeAnalyzer to verify method call
            EmployeeAnalyzer analyzerSpy = spy(new EmployeeAnalyzer());

            BigcompanyApplication.main(new String[]{});

            // Verify the static method was called
            csvParserMock.verify(() -> CsvParser.parseFile(anyString()));
        }
	}
}
