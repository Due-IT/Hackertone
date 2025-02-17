package com.fozzle.project.story.presentation;

import com.fozzle.project.story.dto.response.StoryViewResponse;
import com.fozzle.project.story.service.StoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryQueryController {

    private final StoryQueryService storyQueryService;

    @GetMapping(value = "/stories/{storyId}")
    public ResponseEntity<?> readTickle(
        @PathVariable String storyId
    ) {
        StoryViewResponse response = storyQueryService.readStory(storyId);
        return ResponseEntity.ok(response);
    }

}
