package com.example.smartContactManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "primary_user_seq", sequenceName = "primary_user_seq", allocationSize = 1)
public class PrimaryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_user_seq")
    private Long id;
    private String email;
    private String name;
    @JsonIgnore
    private String password;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "primaryUser")
    private List<Contact> contacts;

}
