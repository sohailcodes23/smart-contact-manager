package com.example.smartContactManager.controller;


import com.example.smartContactManager.entity.Contact;
import com.example.smartContactManager.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id) {

        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid Contact contact) {

        contactService.save(contact);

        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Contact contact) {

        contactService.update(contact);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
