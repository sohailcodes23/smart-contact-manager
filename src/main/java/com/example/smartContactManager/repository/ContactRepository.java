package com.example.smartContactManager.repository;

import com.example.smartContactManager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}


