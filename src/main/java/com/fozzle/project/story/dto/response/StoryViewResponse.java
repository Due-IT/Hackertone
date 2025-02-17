package com.fozzle.project.story.dto.response;

import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.story.entity.Story;
import com.fozzle.project.user.entity.User;
import java.time.LocalDateTime;

public record StoryViewResponse(
    String spotId,
    String spotName,
    String storyId,
    String storyDescription,
    String storyThumbnail,
    String authorName,
    String authorProfileImage,
    LocalDateTime createdAt
) {

    public static StoryViewResponse of(Story story, Spot spot, User user) {
        return new StoryViewResponse(
            spot.getUuid(),
            spot.getName(),
            story.getUuid(),
            story.getDescription(),
            story.getImage(),
            user.getName(),
            user.getProfileImage(),
            story.getCreatedAt()
        );
    }
}
