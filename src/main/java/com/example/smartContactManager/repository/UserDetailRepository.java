package com.example.smartContactManager.repository;

import com.example.smartContactManager.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByUsernameAndStatus(String email, String status);


}
