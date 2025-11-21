package com.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.backend.dtos.RoutineDto;
import com.backend.entities.Routine;

@Mapper(componentModel = "spring")
public interface RoutineMapper {
    RoutineDto toRoutineDto(Routine routine);

    @org.mapstruct.Mapping(target = "place", ignore = true)
    Routine toEntity(RoutineDto routineDto);

    @org.mapstruct.Mapping(target = "place", ignore = true)
    void updateRoutine(RoutineDto routineDto, @MappingTarget Routine routine);
}
