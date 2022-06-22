package com.example.demo.service;

import com.example.demo.PersistenceJPAConfig;
import com.example.demo.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceJPAConfig.class)
public class EventServiceImplTest {

    @Autowired
    private ServiceHR<Event> service;

    @Autowired
    private DataSource dataSource;

    @Test
    public final void whenInvalidEntityIsCreated_thenDataException() {
        service.create(new Event());
    }
}