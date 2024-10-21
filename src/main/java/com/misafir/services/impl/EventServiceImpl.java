package com.misafir.services.impl;

import com.misafir.dto.DtoEvent;
import com.misafir.entities.Event;
import com.misafir.entities.User;
import com.misafir.repositories.EventRepository;
import com.misafir.repositories.UserRepository;
import com.misafir.services.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoEvent createEvent(DtoEvent dtoEvent) {
        // Get the current authenticated user (host)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User host = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Host user not found"));

        // Convert DtoEvent to Event entity
        Event event = new Event();
        BeanUtils.copyProperties(dtoEvent, event);

        // Set the host of the event
        event.setHost(host);

        // Convert String to LocalDate
        try {
            event.setEventDate(LocalDate.parse(dtoEvent.getEventDate()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'YYYY-MM-DD' format.");
        }

        // Save the Event entity
        Event savedEvent = eventRepository.save(event);

        // Convert saved Event back to DtoEvent
        DtoEvent dtoSavedEvent = new DtoEvent();
        BeanUtils.copyProperties(savedEvent, dtoSavedEvent);
        dtoSavedEvent.setEventDate(savedEvent.getEventDate().toString());

        return dtoSavedEvent;
    }

    @Override
    public List<DtoEvent> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DtoEvent convertToDto(Event event) {
        DtoEvent dtoEvent = new DtoEvent();
        BeanUtils.copyProperties(event, dtoEvent);
        dtoEvent.setEventDate(event.getEventDate().toString());
        return dtoEvent;
    }
}
