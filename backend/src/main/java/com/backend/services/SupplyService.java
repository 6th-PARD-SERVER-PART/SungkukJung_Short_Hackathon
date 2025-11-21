package com.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.dtos.SupplyDto;
import com.backend.entities.Place;
import com.backend.mappers.SupplyMapper;
import com.backend.repositories.PlaceRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplyService {
    private final PlaceRepository placeRepository;
    private final SupplyMapper supplyMapper;

    public List<SupplyDto> getSuppliesByPlaceId(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found"));
        return place.getSupplies()
                .stream()
                .map(supplyMapper::toSupplyDto)
                .toList();
    }
}
