package com.example.demo.jpa.entity;

import com.example.demo.model.Employee;
import com.example.demo.model.Event;
import com.example.demo.model.EventType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ManyToManyIntegrationTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("jpa-entity-definition");
        em = emf.createEntityManager();
    }


    @After
    public void destroy() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void whenEventExist_thenCorrect() {

        List<Employee> list = generate_data();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> resultList = query.getResultList();
        Assertions.assertEquals(list, resultList);

        TypedQuery<Employee> query2 = em.createQuery("SELECT employee FROM Employee employee JOIN employee.eventSet event WHERE event.name='Training'", Employee.class);
        List<Employee> resultList2 = query2.getResultList();
        Assertions.assertEquals(list, resultList2);
    }

    private List<Employee> generate_data() {
        Employee employee1 = new Employee("Aaaaa Bbbbb");
        List<Employee> list = new ArrayList<>();
        list.add(employee1);


        LocalDate dateStart = LocalDate.of(2022, 1, 10);
        LocalDate dateEnd = LocalDate.of(2023, 1, 10);
        Event event = new Event("Training", EventType.TRAINING, Date.from(dateStart.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        employee1.getEventSet().add(event);
        event.getEmployeeSet().add(employee1);

        Employee employee2 = new Employee("Ccccc Ddddd");
        list.add(employee2);
        employee2.getEventSet().add(event);
        event.getEmployeeSet().add(employee2);

        persist(event);
        persist(employee1);
        persist(employee2);

        return list;
    }

    private void persist(Object object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }
}
