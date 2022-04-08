package com.example.demo.jpa.entity;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventEntityTest {

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
    public void getAllTest() {
        List<Event> events = new ArrayList<>();

        LocalDate dateStart = LocalDate.of(2022,1,10);
        LocalDate dateEnd = LocalDate.of(2023,1,10);

        Event event1 = new Event("Training", EventType.TRAINING, Date.from(dateStart.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(dateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        events.add(event1);

        persist(event1);
        clearThePersistenceContext();

        List<Event> eventsDB = getEventsFromTable();
        Assertions.assertArrayEquals(events.toArray(), eventsDB.toArray());

        destroy();
    }

    private void persist(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }

    private void clearThePersistenceContext() {
        em.clear();
    }

    private List<Event> getEventsFromTable() {
        String selectQuery = "SELECT event FROM Event event";;
        TypedQuery<Event> selectFromEventTypedQuery = em.createQuery(selectQuery, Event.class);
        return selectFromEventTypedQuery.getResultList();
    }
}
