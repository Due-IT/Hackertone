package com.fozzle.project.story.service;

import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.repository.SpotRepository;
import com.fozzle.project.story.dto.response.StoryViewResponse;
import com.fozzle.project.story.entity.Story;
import com.fozzle.project.story.repository.StoryRepository;
import com.fozzle.project.user.entity.User;
import com.fozzle.project.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class StoryQueryService {

    private final SpotRepository spotRepository;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    public StoryViewResponse readStory(String storyId) {
        Story story = storyRepository.findByUuid(storyId);
        Spot spot = spotRepository.findSpotById(story.getSpotId());
        User user = userRepository.findUserById(story.getAuthorId());

        return StoryViewResponse.of(story, spot, user);
    }
}
