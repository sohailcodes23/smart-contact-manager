package com.example.smartContactManager.repository;

import com.example.smartContactManager.entity.PrimaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrimaryUserlRepository extends JpaRepository<PrimaryUser, Long> {

    Optional<PrimaryUser> findByEmail(String email);

    boolean existsByEmail(String email);


}
