package com.fozzle.project.spot.repository;

import com.fozzle.project.spot.entity.Spot;
import com.fozzle.project.spot.entity.SpotType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {

    List<Spot> findTop6ByCityAndDistrict(String city, String district);

    List<Spot> findSpotsByCityAndDistrictAndType(String city, String district, SpotType type);

    Spot findByUuid(String uuid);

    Spot findSpotById(Long id);

}
