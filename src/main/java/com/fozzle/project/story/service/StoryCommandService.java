package com.fozzle.project.story.service;

import com.fozzle.project.common.util.GcsMediaManager;
import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.repository.SpotRepository;
import com.fozzle.project.story.dto.request.StoryAddRequest;
import com.fozzle.project.story.dto.response.StoryAddResponse;
import com.fozzle.project.story.entity.Story;
import com.fozzle.project.story.repository.StoryRepository;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class StoryCommandService {

    private final SpotRepository spotRepository;
    private final StoryRepository storyRepository;
    private final GcsMediaManager mediaManager;

    public StoryAddResponse addStory(StoryAddRequest request, MultipartFile file) {
        String fileUrl = mediaManager.saveMediaFile(file);

        Spot spot = spotRepository.findByUuid(request.spotId());

        spot.addStory();

        Story story = Story.builder()
            .authorId(1L)
            .spotId(spot.getId())
            .uuid(UUID.randomUUID().toString())
            .description(request.storyDescription())
            .image(fileUrl)
            .build();
        storyRepository.save(story);

        return new StoryAddResponse(spot.getUuid(), story.getUuid());
    }
}
