package com.fozzle.project.spot.presentation;

import com.fozzle.project.spot.dto.SpotDto;
import com.fozzle.project.spot.entity.SpotType;
import com.fozzle.project.spot.service.SpotQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotQueryController {

    private final SpotQueryService spotQueryService;

    @GetMapping(value = "/recommend")
    public ResponseEntity<?> recommendSpot(
        @RequestParam Double nowX,
        @RequestParam Double nowY,
        @RequestParam SpotType type
    ) {
        List<SpotDto> response = spotQueryService.recommendSpot(type);
        return ResponseEntity.ok(response);
    }

}
