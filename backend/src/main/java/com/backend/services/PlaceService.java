package com.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.dtos.PlaceDto;
import com.backend.dtos.RoutineDto;
import com.backend.dtos.SupplyDto;
import com.backend.entities.Place;
import com.backend.mappers.PlaceMapper;
import com.backend.mappers.RoutineMapper;
import com.backend.mappers.SupplyMapper;
import com.backend.repositories.PlaceRepository;
import com.backend.repositories.RoutineRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional()
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final RoutineRepository routineRepository;
    private final PlaceMapper placeMapper;
    private final RoutineMapper routineMapper;
    private final SupplyMapper supplyMapper;

    public List<PlaceDto.PlaceSummaryRequest> getAllPlaces() {
        return placeRepository.findAll()
                .stream()
                .map(placeMapper::toPlaceSummary)
                .toList();
    }

    public PlaceDto.PlaceDetailRequest getPlaceById(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found"));

        // Use repository method to get sorted routines
        List<RoutineDto> routineDtos = routineRepository.findByPlace_PlaceIdOrderByOrderIndexAsc(placeId)
                .stream()
                .map(routineMapper::toRoutineDto)
                .collect(Collectors.toList());

        List<SupplyDto> supplyDtos = place.getSupplies()
                .stream()
                .map(supplyMapper::toSupplyDto)
                .collect(Collectors.toList());

        return new PlaceDto.PlaceDetailRequest(
                place.getPlaceId(),
                place.getPlaceName(),
                routineDtos,
                supplyDtos);
    }
}
