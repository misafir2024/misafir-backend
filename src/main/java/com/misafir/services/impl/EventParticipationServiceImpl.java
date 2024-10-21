package com.misafir.services.impl;

import com.misafir.entities.Event;
import com.misafir.entities.EventParticipation;
import com.misafir.repositories.EventParticipationRepository;
import com.misafir.repositories.EventRepository;
import com.misafir.services.EventParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventParticipationServiceImpl implements EventParticipationService {

    @Autowired
    private EventParticipationRepository eventParticipationRepository;

    @Autowired
    private EventRepository eventRepository;  // Add this to fetch the Event entity

    @Override
    public EventParticipation joinEvent(EventParticipation eventParticipation) {
        // Fetch the Event entity using eventId from the request
        Long eventId = eventParticipation.getEvent().getId();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));

        // Set the event in the EventParticipation entity
        eventParticipation.setEvent(event);

        // Save the EventParticipation entity
        return eventParticipationRepository.save(eventParticipation);
    }
}
