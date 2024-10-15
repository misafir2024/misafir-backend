package com.misafir.services.impl;

import com.misafir.dto.DtoEvent;
import com.misafir.entities.Event;
import com.misafir.repositories.EventRepository;
import com.misafir.services.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public DtoEvent createEvent(DtoEvent dtoEvent) {
        // Convert DtoEvent to Event entity
        Event event = new Event();
        BeanUtils.copyProperties(dtoEvent, event);

        // Save the Event entity to the database
        Event savedEvent = eventRepository.save(event);

        // Convert the saved Event entity back to DtoEvent
        DtoEvent dtoSavedEvent = new DtoEvent();
        BeanUtils.copyProperties(savedEvent, dtoSavedEvent);

        return dtoSavedEvent;
    }

    @Override
    public List<DtoEvent> getAllEvents() {
        // Fetch all Event entities from the repository
        List<Event> events = eventRepository.findAll();

        // Convert List of Event entities to List of DtoEvents
        return events.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Event entity to DtoEvent
    private DtoEvent convertToDto(Event event) {
        DtoEvent dtoEvent = new DtoEvent();
        BeanUtils.copyProperties(event, dtoEvent);
        return dtoEvent;
    }
}
