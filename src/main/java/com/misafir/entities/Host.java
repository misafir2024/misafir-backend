package com.misafir.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "host")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "birthOfDate", nullable = false)
    private Date birthOfDate;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "eventType")
    private String eventType;

    @Column(name = "mealType")
    private String mealType;

    @Column(name = "place")
    private String place;

    @Column(name = "language")
    private String language;

    @ElementCollection
    @CollectionTable(name = "host_amenities", joinColumns = @JoinColumn(name = "host_id"))
    @Column(name = "amenity")
    private List<String> amenities;
}
