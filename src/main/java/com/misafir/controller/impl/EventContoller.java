package com.misafir.controller.impl;

import com.misafir.entities.Event;
import com.misafir.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventContoller {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/event-list")
    public List<Event> getAllHosts() {
        return eventRepository.findAll();
    }
}
