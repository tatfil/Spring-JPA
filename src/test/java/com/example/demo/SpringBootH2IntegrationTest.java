package com.example.demo;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2Application.class)
public class SpringBootH2IntegrationTest {

	private static final Employee AN_EXPECTED_EMPLOYEE = buildEmployee();

	@Autowired
	private EmployeeDAO employeeDAO;

	@org.junit.Test
	public void givenInitData_whenApplicationStarts_thenDataIsLoaded() {
		Iterable<Employee> users = employeeDAO.findAll();

		assertThat(users)
				.hasSize(5)
				.contains(AN_EXPECTED_EMPLOYEE);
	}

	private static Employee buildEmployee() {
		Employee employee = new Employee();
		employee.setId(5L);
		employee.setName("Jess");
		return employee;
	}

}

