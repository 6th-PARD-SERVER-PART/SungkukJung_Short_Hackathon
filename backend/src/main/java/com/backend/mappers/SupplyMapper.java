package com.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.backend.dtos.SupplyDto;
import com.backend.entities.Supply;

@Mapper(componentModel = "spring")
public interface SupplyMapper {
    SupplyDto toSupplyDto(Supply supply);

    @org.mapstruct.Mapping(target = "places", ignore = true)
    Supply toEntity(SupplyDto supplyDto);

    @org.mapstruct.Mapping(target = "places", ignore = true)
    void updateSupply(SupplyDto supplyDto, @MappingTarget Supply supply);
}
