package com.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.dtos.RoutineDto;
import com.backend.mappers.RoutineMapper;
import com.backend.repositories.RoutineRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final RoutineMapper routineMapper;

    public List<RoutineDto> getRoutinesByPlaceId(Long placeId) {
        return routineRepository.findByPlace_PlaceIdOrderByOrderIndexAsc(placeId)
                .stream()
                .map(routineMapper::toRoutineDto)
                .collect(Collectors.toList());
    }
}
