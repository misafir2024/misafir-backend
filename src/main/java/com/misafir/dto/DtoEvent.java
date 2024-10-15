package com.misafir.dto;

import lombok.Data;

@Data
public class DtoEvent {
    private Long id;
    private String eventName;  // Corresponds to `title` in the entity
    private String eventDescription;
    private String eventLocation;
    private String eventDate;  // Ensure this matches the entity type (String for date or LocalDate)
}
