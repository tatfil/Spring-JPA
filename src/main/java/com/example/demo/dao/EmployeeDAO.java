package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    Employee getById(Long id);


//    @Query("SELECT f FROM Employee f WHERE LOWER(f.name) = LOWER(:name)")
//    Employee retrieveByName(@Param("name") String name);


}
