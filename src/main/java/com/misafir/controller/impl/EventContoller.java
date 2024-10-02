package com.misafir.controller.impl;

import com.misafir.entities.Event;
import com.misafir.services.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventContoller {

    @Autowired
    EventService eventService;

    @GetMapping("/event-list")
    public List<Event> getAllHosts() {
        return eventService.getEventList();
    }
}
