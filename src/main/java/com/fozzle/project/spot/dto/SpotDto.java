package com.fozzle.project.spot.dto;

import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;

public record SpotDto(
    String uuid,
    String name,
    Double x,
    Double y,
    String address,
    int likes,
    String thumbnail,
    int storyCount,
    SpotType type
) {

    public static SpotDto of(Spot spot){
        return new SpotDto(
            spot.getUuid(),
            spot.getName(),
            spot.getX(),
            spot.getY(),
            spot.getCity() + " " + spot.getDistrict(),
            spot.getLikes(),
            spot.getThumbnail(),
            spot.getStoryCount(),
            spot.getType()
        );
    }
}
