package com.example.smartContactManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", allocationSize = 1)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    private Long id;
    @NotBlank(message = "firstName should not be null")
    private String firstName;
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid Email")
    private String email;

    @NotNull(message = "Mobile No. should not be null")
    @Min(value = 10, message = "Invalid no")
    @Max(value = 10, message = "Invalid no")
    private String mobileNo;

    private String workMobileNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "primary_user_id", nullable = false)
    private PrimaryUser primaryUser;
}
