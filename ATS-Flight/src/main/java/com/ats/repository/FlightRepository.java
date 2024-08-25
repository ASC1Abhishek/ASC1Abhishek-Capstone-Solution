package com.ats.repository;

import com.ats.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, String> {
}
