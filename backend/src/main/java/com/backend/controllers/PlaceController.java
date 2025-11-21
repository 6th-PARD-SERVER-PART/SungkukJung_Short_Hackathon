package com.backend.controllers;

import com.backend.dtos.PlaceDto;

import com.backend.services.PlaceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("")
    public ResponseEntity<List<PlaceDto.PlaceSummaryRequest>> getAllPlaces() {
        List<PlaceDto.PlaceSummaryRequest> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDto.PlaceDetailRequest> getPlaceById(@PathVariable Long id) {
        PlaceDto.PlaceDetailRequest place = placeService.getPlaceById(id);
        return ResponseEntity.ok(place);
    }
}
