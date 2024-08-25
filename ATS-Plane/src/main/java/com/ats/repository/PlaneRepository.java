package com.ats.repository;

import com.ats.entity.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends JpaRepository <PlaneEntity, Long> {
    PlaneEntity findTopByOrderByRegistrationNumberDesc();
}
