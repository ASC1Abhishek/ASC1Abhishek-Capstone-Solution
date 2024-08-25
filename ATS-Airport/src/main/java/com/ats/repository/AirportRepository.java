package com.ats.repository;

import com.ats.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirportRepository extends JpaRepository <AirportEntity, Long> {
    AirportEntity findTopByOrderByAirportCodeDesc();

}
