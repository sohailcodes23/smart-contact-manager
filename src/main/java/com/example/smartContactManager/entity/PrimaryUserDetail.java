package com.example.smartContactManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name = "primary_user_detail_seq", sequenceName = "primary_user_detail_seq", allocationSize = 1)
public class PrimaryUserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_user_detail_seq")
    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;
    private String role;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;


}
