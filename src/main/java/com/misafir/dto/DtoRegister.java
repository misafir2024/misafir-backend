// DtoRegister.java
package com.misafir.dto;

import lombok.Data;
import java.util.List;

@Data
public class DtoRegister {
    private String username;
    private String email;
    private String password;
    private String firstName;  // Add this field
    private String lastName;   // Add this field

    // Host-specific fields
    private String eventType;
    private String mealType;
    private String place;
    private String language;
    private List<String> amenities;
}
