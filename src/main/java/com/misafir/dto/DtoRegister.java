package com.misafir.dto;

import lombok.Data;

import java.util.List;

@Data
public class DtoRegister {

    private String username;
    private String email;
    private String password;

    // Host-specific fields
    private String eventType;
    private String mealType;
    private String place;
    private String language;
    private List<String> amenities;

    // Getters and setters if not using Lombok
}
