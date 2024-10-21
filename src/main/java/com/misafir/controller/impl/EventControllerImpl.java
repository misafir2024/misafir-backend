package com.misafir.controller.impl;

import com.misafir.dto.DtoEvent;
import com.misafir.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EventControllerImpl {

    @Autowired
    private EventService eventService;

    private static final Logger logger = Logger.getLogger(EventControllerImpl.class.getName());

    @PostMapping
    public ResponseEntity<DtoEvent> createEvent(@RequestBody DtoEvent dtoEvent) {
        // Log the incoming event for debugging
        logger.info("Received request to create event: " + dtoEvent);

        // Call the service to create the event
        DtoEvent createdEvent = eventService.createEvent(dtoEvent);

        // Log the created event and return the response
        logger.info("Event created successfully: " + createdEvent);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @GetMapping
    public List<DtoEvent> getAllEvents() {
        logger.info("Fetching all events");
        return eventService.getAllEvents();
    }
}
