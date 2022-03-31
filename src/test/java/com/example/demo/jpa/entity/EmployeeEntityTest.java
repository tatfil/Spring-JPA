package com.example.demo.jpa.entity;

import com.example.demo.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class EmployeeEntityTest {

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
    public void getAllTest(){
        List<Employee> employees = new ArrayList<>();
        Employee employeeA = new Employee("Arnold Schwarzenegger");
        Employee employeeB = new Employee("B Schwarzenegger");
        employees.add(employeeA);
        employees.add(employeeB);

        persist(employeeA);
        persist(employeeB);

        clearThePersistenceContext();
        List<Employee> employeesDB = getEmployeesFromTable();
        Assertions.assertArrayEquals(employees.toArray(), employeesDB.toArray());


    }
    private void persist(Employee employee) {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    private void clearThePersistenceContext() {
        em.clear();
    }

    private List<Employee> getEmployeesFromTable() {
        String selectQuery = "SELECT employee FROM Employee employee";;
        TypedQuery<Employee> selectFromEmployeeTypedQuery = em.createQuery(selectQuery, Employee.class);
        return selectFromEmployeeTypedQuery.getResultList();
    }
}
