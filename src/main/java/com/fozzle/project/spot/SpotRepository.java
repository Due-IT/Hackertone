package com.fozzle.project.spot;

import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot>  findSpotsByType(SpotType type);

}
