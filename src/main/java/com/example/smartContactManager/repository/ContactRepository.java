package com.example.smartContactManager.repository;

import com.example.smartContactManager.entity.Contact;
import com.example.smartContactManager.entity.PrimaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByPrimaryUser(PrimaryUser primaryUser);

    Optional<Contact> findByIdAndPrimaryUser(Long id, PrimaryUser primaryUser);
}


