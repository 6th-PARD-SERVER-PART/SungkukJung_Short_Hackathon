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
@Table(name = "supply")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_id")
    private Long supplyId;

    @Column(name = "supply_name")
    private String supplyName;

    // Many-to-Many: Supply can be in many Places (optional, inverse side)
    @ManyToMany(mappedBy = "supplies")
    @Builder.Default
    private List<Place> places = new ArrayList<>();
}
