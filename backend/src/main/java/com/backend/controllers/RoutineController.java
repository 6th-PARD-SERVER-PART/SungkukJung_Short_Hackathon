package com.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.RoutineDto;
import com.backend.services.RoutineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/places/{placeId}/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<List<RoutineDto>> getRoutinesByPlace(@PathVariable Long placeId) {
        List<RoutineDto> routines = routineService.getRoutinesByPlaceId(placeId);
        return ResponseEntity.ok(routines);
    }
}
