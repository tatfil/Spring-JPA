package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {
/*
* in order to start leveraging the Spring Data programming model with JPA, a DAO interface needs to extend the JPA specific Repository interface, JpaRepository. This will enable Spring Data to find this interface and automatically create an implementation for it.

By extending the interface, we get the most relevant CRUD methods for standard data access available in a standard DAO.
*
* */



    Employee getById(Long id);


//    @Query("SELECT f FROM Employee f WHERE LOWER(f.name) = LOWER(:name)")
//    Employee retrieveByName(@Param("name") String name);


}
