package com.misafir.controller.impl;

import com.misafir.controller.IEventParticipationController;
import com.misafir.dto.DtoEventParticipation;
import com.misafir.entities.Event;
import com.misafir.entities.EventParticipation;
import com.misafir.entities.User;
import com.misafir.repositories.EventRepository;
import com.misafir.repositories.UserRepository;
import com.misafir.services.EventParticipationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-participation")
public class EventParticipationControllerImpl implements IEventParticipationController {

    @Autowired
    private EventParticipationService eventParticipationService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;  // To fetch the participant (User)

    @Override
    @PostMapping
    public ResponseEntity<DtoEventParticipation> joinEvent(@RequestBody DtoEventParticipation dtoEventParticipation) {
        EventParticipation eventParticipation = new EventParticipation();

        // Fetch the event using the eventId
        Event event = eventRepository.findById(dtoEventParticipation.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + dtoEventParticipation.getEventId()));

        // Fetch the participant (User) using the userId
        User participant = userRepository.findById(dtoEventParticipation.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dtoEventParticipation.getUserId()));

        // Set the event and participant in the EventParticipation object
        eventParticipation.setEvent(event);
        eventParticipation.setParticipant(participant);

        // Copy other properties from DTO to entity
        BeanUtils.copyProperties(dtoEventParticipation, eventParticipation, "event", "participant");

        // Join the event (save to the database)
        eventParticipationService.joinEvent(eventParticipation);
        return ResponseEntity.status(201).body(dtoEventParticipation);
    }
}
