package com.fozzle.project.spot.service;

import com.fozzle.project.spot.dto.SpotDto;
import com.fozzle.project.spot.dto.SpotRecommendResponse;
import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;
import com.fozzle.project.spot.repository.SpotRepository;
import com.fozzle.project.story.repository.StoryRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotQueryService {

    private final SpotRepository spotRepository;
    private final StoryRepository storyRepository;

    public SpotRecommendResponse recommendAroundSpot(Double nowX, Double nowY, SpotType type) {
        //TODO 위치 기반으로 추천하는 기능 구현 필요
        List<Spot> spots;
        if (type.equals(SpotType.ALL)) {
            spots = spotRepository.findTop6ByCityAndDistrict("부산광역시", "수영구");

        } else {
            spots = spotRepository.findSpotsByCityAndDistrictAndType("부산광역시", "수영구", type);
        }

        List<Double> center = calculateCenter(spots);

        List<Spot> sorted = sortByNearest(spots);
        List<SpotDto> result = sorted.stream()
            .map(SpotDto::of)
            .toList();

        return SpotRecommendResponse.of(result, center);
    }

    public SpotRecommendResponse recommendDistrictSpot(String city, String district,
        SpotType type) {
        List<Spot> spots;
        if (type.equals(SpotType.ALL)) {
            spots = spotRepository.findTop6ByCityAndDistrict(city, district);
        } else {
            spots = spotRepository.findSpotsByCityAndDistrictAndType(city, district, type);
        }

        List<Double> center = calculateCenter(spots);

        List<Spot> sorted = sortByNearest(spots);
        List<SpotDto> result = sorted.stream()
            .map(SpotDto::of)
            .toList();

        return SpotRecommendResponse.of(result, center);
    }

    private List<Spot> sortByNearest(List<Spot> spots) {
        if (spots.isEmpty()) {
            return spots;
        }

        List<Spot> result = new ArrayList<>();
        Set<Spot> visited = new HashSet<>();
        Spot current = spots.get(0); // 시작점
        result.add(current);
        visited.add(current);

        while (result.size() < spots.size()) {
            Spot nearest = null;
            double minDistance = Double.MAX_VALUE;

            for (Spot spot : spots) {
                if (!visited.contains(spot)) {
                    double dist = current.distanceTo(spot);
                    if (dist < minDistance) {
                        minDistance = dist;
                        nearest = spot;
                    }
                }
            }

            if (nearest != null) {
                result.add(nearest);
                visited.add(nearest);
                current = nearest;
            }
        }

        return result;
    }

    private List<Double> calculateCenter(List<Spot> spots) {
        double sumX = 0;
        double sumY = 0;
        for (Spot spot : spots) {
            sumX += spot.getX();
            sumY += spot.getY();
        }

        double centerX = sumX / spots.size();
        double centerY = sumY / spots.size();

        return List.of(centerX, centerY);
    }

    public List<String> readStoryIds(String spotId) {
        Spot spot = spotRepository.findByUuid(spotId);

        List<String> list = storyRepository.findStoriesBySpotId(spot.getId())
            .stream()
            .map(story -> story.getUuid())
            .toList();

        return list;
    }
}
