package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private Date started;
    private Date ended;
    private String notes;

    @ManyToMany(mappedBy = "eventSet")
    private Set<Employee> employeeSet = new HashSet<>();

    public Event() {
    }

    public Event(String name, EventType type, Date started, Date ended, String notes) {
        this.name = name;
        this.type = type;
        this.started = started;
        this.ended = ended;
        this.notes = notes;
    }

    public Event(Long id, String name, EventType type, Date started, Date ended, String notes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.started = started;
        this.ended = ended;
        this.notes = notes;
    }

    public Event(String name, EventType type, Date started, Date ended) {
        this.name = name;
        this.type = type;
        this.started = started;
        this.ended = ended;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(started, event.started) && Objects.equals(ended, event.ended) && Objects.equals(notes, event.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, started, ended, notes);
    }
}
