package com.example.smartContactManager.repository;

import com.example.smartContactManager.entity.PrimaryUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrimaryUserDetailRepository extends JpaRepository<PrimaryUserDetail, Long> {

    Optional<PrimaryUserDetail> findByUsernameAndStatus(String email, String status);


}
