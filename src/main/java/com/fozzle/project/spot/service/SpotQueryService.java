package com.fozzle.project.spot.service;

import com.fozzle.project.spot.SpotRepository;
import com.fozzle.project.spot.dto.SpotDto;
import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class SpotQueryService {

    private final SpotRepository spotRepository;

    public List<SpotDto> recommendSpot(SpotType type) {
        //TODO 위치 기반으로 추천하는 기능 구현 필요

        List<Spot> spots = spotRepository.findSpotsByType(type);
        List<SpotDto> result = spots.stream()
            .map(SpotDto::of)
            .toList();

        return result;
    }
}
