package com.misafir.controller.impl;

import com.misafir.controller.IEventController;
import com.misafir.dto.DtoEvent;
import com.misafir.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerImpl implements IEventController {

    @Autowired
    private EventService eventService;

    @Override
    @PostMapping
    public ResponseEntity<DtoEvent> createEvent(@RequestBody DtoEvent dtoEvent) {
        // Call the service layer to create an event
        DtoEvent createdEvent = eventService.createEvent(dtoEvent);

        // Return a response with the created DtoEvent
        return ResponseEntity.status(201).body(createdEvent);
    }

    @Override
    @GetMapping
    public List<DtoEvent> getAllEvents() {
        // Call the service layer to get all events
        return eventService.getAllEvents();
    }
}
