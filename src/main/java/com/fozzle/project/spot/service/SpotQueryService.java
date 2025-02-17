package com.fozzle.project.spot.service;

import com.fozzle.project.spot.dto.SpotDto;
import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;
import com.fozzle.project.spot.repository.SpotRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotQueryService {

    private final SpotRepository spotRepository;

    public List<SpotDto> recommendAroundSpot(Double nowX, Double nowY, SpotType type) {
        //TODO 위치 기반으로 추천하는 기능 구현 필요
        List<Spot> spots;
        if (type.equals(SpotType.ALL)) {
            spots = spotRepository.findSpotsByCityAndDistrict("부산광역시", "수영구");

        } else {
            spots = spotRepository.findSpotsByType(type);
        }
        sortByCoordinates(spots);
        List<SpotDto> result = spots.stream()
            .map(SpotDto::of)
            .toList();

        return result;
    }

    public List<SpotDto> recommendDistrictSpot(String city, String district, SpotType type) {
        List<Spot> spots;
        if (type.equals(SpotType.ALL)) {
            spots = spotRepository.findSpotsByCityAndDistrict(city, district);
        } else {
            spots = spotRepository.findSpotsByCityAndDistrictAndType(city, district, type);
        }
        sortByCoordinates(spots);
        List<SpotDto> result = spots.stream()
            .map(SpotDto::of)
            .toList();

        return result;
    }

    private static void sortByCoordinates(List<Spot> spots) {
        spots.sort((s1, s2) -> {
            if (s1.getX() != s2.getX()) {
                return Double.compare(s1.getX(), s2.getX());
            }
            return Double.compare(s1.getY(), s2.getY());
        });
    }
}
