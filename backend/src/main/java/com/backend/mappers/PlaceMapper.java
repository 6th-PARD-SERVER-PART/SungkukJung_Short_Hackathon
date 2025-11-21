package com.backend.mappers;

import com.backend.dtos.PlaceDto;
import com.backend.entities.Place;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    // Map Place entity to summary DTO (for list view)
    PlaceDto.PlaceSummaryRequest toPlaceSummary(Place place);
}
