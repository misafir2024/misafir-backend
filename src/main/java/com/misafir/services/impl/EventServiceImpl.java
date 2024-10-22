package com.misafir.services.impl;

import com.misafir.dto.DtoEvent;
import com.misafir.entities.Event;
import com.misafir.entities.EventImage;
import com.misafir.entities.User;
import com.misafir.repositories.EventRepository;
import com.misafir.repositories.UserRepository;
import com.misafir.services.EventService;
import com.misafir.services.ImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoEvent createEvent(DtoEvent dtoEvent, List<MultipartFile> images) throws IOException {
        // Log start of event creation
        System.out.println("Starting event creation process.");

        // Log incoming event details
        System.out.println("Incoming event details: " + dtoEvent.toString());

        Event event = new Event();
        BeanUtils.copyProperties(dtoEvent, event);

        // Ensure the eventDate is parsed from string to LocalDate
        System.out.println("Parsing event date: " + dtoEvent.getEventDate());
        event.setEventDate(LocalDate.parse(dtoEvent.getEventDate()));

        // Retrieve the current user from SecurityContext (assuming you're using Spring Security)
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Current logged-in email: " + currentUsername);

        // Handle Optional correctly, find by email instead of username
        User host = userRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new RuntimeException("Host user not found"));
        System.out.println("Host user found: " + host.toString());

        // Set the host for the event
        event.setHost(host);

        // Handle image upload
        List<EventImage> eventImages = new ArrayList<>();
        for (MultipartFile image : images) {
            System.out.println("Uploading image...");
            String imageUrl = imageService.saveImage(image);
            EventImage eventImage = new EventImage();
            eventImage.setImageUrl(imageUrl);
            eventImage.setEvent(event);
            eventImages.add(eventImage);
            System.out.println("Image uploaded successfully: " + imageUrl);
        }

        event.setImages(eventImages); // Assign images to the event

        // Save the event and log the saved details
        Event savedEvent = eventRepository.save(event);
        System.out.println("Event saved successfully: " + savedEvent.toString());

        // Convert to DtoEvent for response
        DtoEvent savedDtoEvent = new DtoEvent();
        BeanUtils.copyProperties(savedEvent, savedDtoEvent);
        System.out.println("Event creation process completed successfully.");

        return savedDtoEvent;
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
