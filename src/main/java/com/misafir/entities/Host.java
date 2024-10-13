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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Date birthOfDate;

    @Column(nullable = false)
    private String email;

    private String eventType;
    private String mealType;
    private String place;
    private String language;

    @ElementCollection
    @CollectionTable(name = "host_amenities", joinColumns = @JoinColumn(name = "host_id"))
    @Column(name = "amenity")
    private List<String> amenities;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Listing> listings;
}
