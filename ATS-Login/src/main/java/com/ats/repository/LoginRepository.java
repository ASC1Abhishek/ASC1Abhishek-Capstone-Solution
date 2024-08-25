package com.ats.repository;

import com.ats.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    Optional<LoginEntity> findByEmail(String email);
}
