package com.backend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PlaceDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class PlaceSummaryRequest {
        private Long placeId;
        private String placeName;
        private String placeImage;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PlaceDetailRequest {
        private Long placeId;
        private String placeName;
        private List<RoutineDto> routines;
        private List<SupplyDto> supplies;
    }
}
