package com.fozzle.project.spot.presentation;

import com.fozzle.project.spot.dto.SpotDto;
import com.fozzle.project.spot.dto.SpotRecommendResponse;
import com.fozzle.project.spot.entity.SpotType;
import com.fozzle.project.spot.service.SpotQueryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotQueryController {

    private final SpotQueryService spotQueryService;

    @GetMapping(value = "/recommend/around")
    public ResponseEntity<?> recommendAroundSpot(
        @RequestParam Double nowX,
        @RequestParam Double nowY,
        @RequestParam SpotType type
    ) {
        SpotRecommendResponse response = spotQueryService.recommendAroundSpot(nowX, nowY, type);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/recommend/district")
    @Operation(summary = "지역기준 스팟 목록 조회",
        description = "스팟 목록을 조회합니다.<br>"
            + "city : 부산광역시<br>"
            + "district : 수영구, 해운대구, 연제구<br>"
    )
    public ResponseEntity<?> recommendDistrictSpot(
        @RequestParam(defaultValue = "부산광역시") String city,
        @RequestParam String district,
        @RequestParam SpotType type
    ) {
        SpotRecommendResponse response = spotQueryService.recommendDistrictSpot(city, district, type);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/spots/{spotId}/story-list")
    @Operation(summary = "스토리 목록 조회",
        description = "스토리 목록을 조회합니다.<br>"
            + "현재 스토리가 있는 spotId는 다음과 같습니다.<br>"
            + "엘까르니따스 광안리점 : 36638389-4bca-477c-b1b6-1f1d781f333a"
    )
    public ResponseEntity<?> readStoryIds(
        @PathVariable String spotId
    ) {
        List<String> response = spotQueryService.readStoryIds(spotId);
        return ResponseEntity.ok(response);
    }

}
