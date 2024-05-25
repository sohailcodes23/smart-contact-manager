package com.example.smartContactManager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", allocationSize = 1)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    private Long id;

    private String firstName;
    private String lastName;
    private Integer mobile;
    private Integer workMobile;

    @ManyToOne
    @JoinColumn(name = "primary_user_id", nullable = false)
    private PrimaryUser primaryUser;
}
