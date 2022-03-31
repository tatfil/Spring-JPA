package com.example.demo.service;

//import com.example.demo.PersistenceConfig;
import com.example.demo.PersistenceJPAConfig;
import com.example.demo.model.Employee;
import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = PersistenceJPAConfig.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
class EmployeeServiceImplTest {

    @Autowired
    private ServiceHR<Employee> service;

    @Autowired
    private DataSource dataSource;

    @Test()
    public final void whenInvalidEntityIsCreated_thenDataException() {
        service.create(new Employee());//without error
        Assert.assertThrows(DataIntegrityViolationException.class, (ThrowingRunnable) service.create(new Employee()));

    }

}