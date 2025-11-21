package com.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_image")
    private String placeImage;

    // One-to-Many: One Place has many Routines
    @OneToMany(mappedBy = "place", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    @Builder.Default
    private List<Routine> routines = new ArrayList<>();

    // Many-to-Many: Place can have many Supplies, Supply can be in many Places
    @ManyToMany
    @JoinTable(name = "place_supply", joinColumns = @JoinColumn(name = "place_id"), inverseJoinColumns = @JoinColumn(name = "supply_id"))
    @Builder.Default
    private List<Supply> supplies = new ArrayList<>();
}
