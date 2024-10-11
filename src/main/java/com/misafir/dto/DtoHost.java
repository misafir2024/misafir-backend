package com.misafir.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DtoHost {
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Birth date is required")
    private Date birthOfDate;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    private String eventType; // garden_party, dinner, etc.
    private String mealType; // vegan, non-vegan
    private String place; // cycladic_home, etc.
    private String language; // de, en, fr
    private List<String> amenities; // wifi, tv, etc.
}
