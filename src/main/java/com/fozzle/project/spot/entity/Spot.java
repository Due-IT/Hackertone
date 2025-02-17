package com.fozzle.project.spot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "spots")
@EntityListeners(AuditingEntityListener.class)
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false, unique = true)
    private String uuid;

    @Column(length = 100, nullable = false)
    private String name;

    private Double x;

    private Double y;

    private String city;

    private String district;

    private int likes;

    private String thumbnail;

    private int storyCount;

    @Enumerated(EnumType.STRING)
    private SpotType type;

    @Builder
    public Spot(Long authorId, String uuid, Double x, Double y, String name, SpotType type) {
        this.uuid = uuid;
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
