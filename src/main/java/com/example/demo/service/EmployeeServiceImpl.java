package com.example.demo.service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements ServiceHR<Employee> {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Employee create(Employee employee) {
        return employeeDAO.save(employee);
    }
}
