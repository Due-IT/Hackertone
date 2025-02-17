package com.fozzle.project.spot.dto;

import java.util.List;

public record SpotRecommendResponse(
    double centerX,
    double centerY,
    List<SpotDto> spots
) {

    public static SpotRecommendResponse of(List<SpotDto> spots, List<Double> center) {
        return new SpotRecommendResponse(center.get(0), center.get(1), spots);
    }

}
