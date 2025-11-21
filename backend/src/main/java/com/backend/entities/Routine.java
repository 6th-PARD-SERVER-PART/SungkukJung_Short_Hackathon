package com.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long routineId;

    @Column(name = "order_index")
    private Long orderIndex;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "routine_image")
    private String routineImage;

    @Column(name = "is_complete")
    private Boolean isComplete;
}
