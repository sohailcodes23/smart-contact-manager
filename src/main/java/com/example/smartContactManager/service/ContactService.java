package com.example.smartContactManager.service;

import com.example.smartContactManager.entity.Contact;
import com.example.smartContactManager.entity.PrimaryUser;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import com.example.smartContactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PrimaryUserService primaryUserService;


    public List<Contact> findAll() {
        PrimaryUser primaryUser = primaryUserService.getAuthenticatedUser();
        return contactRepository.findAllByPrimaryUser(primaryUser);
    }

    public Contact findById(Long id) {
        PrimaryUser primaryUser = primaryUserService.getAuthenticatedUser();
        return contactRepository.findByIdAndPrimaryUser(id, primaryUser)
                .orElseThrow(() -> new ResourceNotFoundException("Contact"));
    }

    public void save(Contact contact) {
        PrimaryUser primaryUser = primaryUserService.getAuthenticatedUser();
        contact.setPrimaryUser(primaryUser);
        contactRepository.save(contact);
    }

    public void update(Contact contact) {

        Contact updatedContact = findById(contact.getId());
        contactRepository.save(updatedContact);
    }


    public void deleteById(Long id) {
        Contact contact = findById(id);
        contactRepository.delete(contact);
    }
}
