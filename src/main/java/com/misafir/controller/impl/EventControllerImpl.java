package com.misafir.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misafir.dto.DtoEvent;
import com.misafir.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EventControllerImpl {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<DtoEvent> createEvent(
            @RequestPart("event") String eventJson,
            @RequestPart("images") List<MultipartFile> images) throws IOException {

        // Convert JSON string into DtoEvent object
        ObjectMapper objectMapper = new ObjectMapper();
        DtoEvent dtoEvent = objectMapper.readValue(eventJson, DtoEvent.class);

        DtoEvent createdEvent = eventService.createEvent(dtoEvent, images);
        return ResponseEntity.status(201).body(createdEvent);
    }

    @GetMapping
    public List<DtoEvent> getAllEvents() {
        return eventService.getAllEvents();
    }
}
