package com.fozzle.project.story.presentation;

import com.fozzle.project.story.dto.request.StoryAddRequest;
import com.fozzle.project.story.dto.response.StoryAddResponse;
import com.fozzle.project.story.service.StoryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryCommandController {

    private final StoryCommandService storyCommandService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addStory(
        @RequestPart StoryAddRequest request,
        @RequestPart MultipartFile file
    ) {
        StoryAddResponse response = storyCommandService.addStory(request, file);
        return ResponseEntity.ok(response);
    }

}
