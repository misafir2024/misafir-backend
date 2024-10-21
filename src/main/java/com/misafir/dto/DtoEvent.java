package com.misafir.dto;

import lombok.Data;

@Data
public class DtoEvent {
    private Long id;
    private String title;           // Matches `title` in the Event entity
    private String description;     // Matches `description` in the Event entity
    private String location;        // Matches `location` in the Event entity
    private String eventDate;       // Ensure this is correctly converted in service layer
    private int maxParticipants;
    private double price;
}
