package com.example.demo.dao;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventDAO extends JpaRepository<Event, Long> {

    Event findByName(String name);

    @Query("SELECT f FROM Event f WHERE LOWER(f.name) = LOWER(:name)")
    Event retrieveByName(@Param("name") String name);
}
