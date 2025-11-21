package com.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dtos.SupplyDto;
import com.backend.services.SupplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/places/{placeId}/supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;

    @GetMapping
    public ResponseEntity<List<SupplyDto>> getSuppliesByPlace(@PathVariable Long placeId) {
        List<SupplyDto> supplies = supplyService.getSuppliesByPlaceId(placeId);
        return ResponseEntity.ok(supplies);
    }
}
