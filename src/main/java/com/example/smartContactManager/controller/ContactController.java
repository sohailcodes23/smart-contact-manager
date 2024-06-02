package com.example.smartContactManager.controller;


import com.example.smartContactManager.entity.Contact;
import com.example.smartContactManager.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> findAll(Principal principal) {
        return ResponseEntity.ok(contactService.findAll(principal));
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id, Principal principal) {

        return ResponseEntity.ok(contactService.findById(id, principal));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Contact contact, Principal principal) {

        contactService.save(contact, principal);

        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Contact contact, Principal principal) {

        contactService.update(contact, principal);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id, Principal principal) {

        contactService.deleteById(id, principal);
        return ResponseEntity.noContent().build();
    }
}
