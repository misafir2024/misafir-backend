package com.misafir.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // New fields for user's full name
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    // Host-specific fields
    private String eventType;
    private String mealType;
    private String place;
    private String language;

    @ElementCollection
    @CollectionTable(name = "user_amenities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    // Relationships
    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> hostedEvents;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipation> eventParticipations;

    // New method to return the full name of the user
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
