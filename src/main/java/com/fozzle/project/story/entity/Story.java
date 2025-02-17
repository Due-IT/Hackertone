package com.fozzle.project.story.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "stories")
@EntityListeners(AuditingEntityListener.class)
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authorId;

    private Long spotId;

    @Column(length = 36, nullable = false, unique = true)
    private String uuid;

    @Lob
    private String image;

    @Column(length = 300, nullable = false)
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Story(Long authorId, Long spotId, String uuid, String image, String description) {
        this.authorId = authorId;
        this.spotId = spotId;
        this.uuid = uuid;
        this.image = image;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

}
