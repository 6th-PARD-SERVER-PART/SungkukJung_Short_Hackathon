package com.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoutineDto {
    private Long routineId;
    private Long orderIndex;
    private String title;
    private String description;
    private String routineImage;
    private Boolean isComplete;
}
