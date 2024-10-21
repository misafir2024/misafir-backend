package com.misafir.services.impl;

import com.misafir.dto.DtoEvent;
import com.misafir.entities.Event;
import com.misafir.entities.User;
import com.misafir.repositories.EventRepository;
import com.misafir.repositories.UserRepository;
import com.misafir.services.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private UserRepository userRepository;  // Inject UserRepository to fetch the authenticated user

    @Override
    public DtoEvent createEvent(DtoEvent dtoEvent) {
        // Convert DtoEvent to Event entity
        Event event = new Event();
        BeanUtils.copyProperties(dtoEvent, event);

        // Convert String to LocalDate
        try {
            event.setEventDate(LocalDate.parse(dtoEvent.getEventDate()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'YYYY-MM-DD' format.");
        }

        // Fetch the currently authenticated user (the host)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            email = userDetails.getUsername();  // Get the email (or username) of the authenticated user
        }

        if (email != null) {
            // Fetch the User from the database using the email
            User host = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Set the host for the event
            event.setHost(host);
        } else {
            throw new IllegalArgumentException("Cannot find authenticated user");
        }

        // Save the Event entity
        Event savedEvent = eventRepository.save(event);

        // Convert saved Event back to DtoEvent
        DtoEvent dtoSavedEvent = new DtoEvent();
        BeanUtils.copyProperties(savedEvent, dtoSavedEvent);
        dtoSavedEvent.setEventDate(savedEvent.getEventDate().toString());  // Convert LocalDate back to String

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
        dtoEvent.setEventDate(event.getEventDate().toString());  // Convert LocalDate to String
        return dtoEvent;
    }
}
