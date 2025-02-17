package com.fozzle.project.story.repository;

import com.fozzle.project.story.entity.Story;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    Story findByUuid(String uuid);

    List<Story> findStoriesBySpotId(Long id);
}
