package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Sql({"/import_employees.sql"})
public class SpringBootInitialLoadIntegrationTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void testLoadDataForTestClass() {
        List<Employee> employees = employeeDAO.findAll();

        assertEquals(6, employeeDAO.findAll().size());
        assertTrue(employees.contains(new Employee(6L, "Joy")));
    }
}