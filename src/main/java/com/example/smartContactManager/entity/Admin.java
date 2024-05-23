package com.example.smartContactManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 1)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    private Long id;
    private String username;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    // Creating OneToOne relation bcz each user detail has exactly one role.
    // Each userDetail is associated with exactly one role, maintaining data integrity and clarity in the system.
    @OneToOne
    private PrimaryUser primaryUser;

}
