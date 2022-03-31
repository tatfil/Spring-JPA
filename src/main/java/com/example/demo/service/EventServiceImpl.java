package com.example.demo.service;

import com.example.demo.dao.EventDAO;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements ServiceHR<Event>{

    @Autowired
    private EventDAO eventDAO;

    @Override
    public Event create(Event event) {
        return eventDAO.save(event);
    }


}
