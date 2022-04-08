package com.example.demo.jpa.entity;

import com.example.demo.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CRUDTest {

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
    public void CRUDEmployeeTest(){
        //CREATE
        Employee employee = new Employee( "Arnold Schwarzenegger");
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();

        Employee employeeDB = em.find( Employee.class, 1L );
        Assertions.assertEquals(employee, employeeDB);


        //UPDATE
        em.getTransaction().begin();
        String name = "Another Name";
        employeeDB.setName(name);
        em.getTransaction().commit();
        Assertions.assertEquals(name, em.find( Employee.class, 1L ).getName());

        //DELETE
        em.getTransaction().begin();
        em.remove(employeeDB);
        em.getTransaction().commit();
        Assertions.assertNull(em.find( Employee.class, 1L ));
    }
}
