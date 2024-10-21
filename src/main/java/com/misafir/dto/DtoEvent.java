package com.misafir.dto;

import lombok.Data;
import java.util.List;

@Data
public class DtoEvent {
    private Long id;
    private String title;           // Matches `title` in the Event entity
    private String description;     // Matches `description` in the Event entity
    private String location;        // Matches `location` in the Event entity
    private String eventDate;       // Ensure this is correctly converted in the service layer
    private int maxParticipants;
    private double price;
    private String eventType;
    private String mealType;
    private List<String> amenities;
    private String hostName; // Name of the host user
}
