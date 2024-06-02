package com.example.smartContactManager.service;

import com.example.smartContactManager.entity.Contact;
import com.example.smartContactManager.entity.PrimaryUser;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import com.example.smartContactManager.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ContactService {


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PrimaryUserService primaryUserService;

    public List<Contact> findAll(Principal principal) {
        PrimaryUser primaryUser = primaryUserService.findPrimaryUserByPrincipal(principal);
        return contactRepository.findAllByPrimaryUser(primaryUser);
    }

    public Contact findById(Long id, Principal principal) {
        PrimaryUser primaryUser = primaryUserService.findPrimaryUserByPrincipal(principal);
        return contactRepository.findByIdAndPrimaryUser(id, primaryUser)
                .orElseThrow(() -> new ResourceNotFoundException("Contact"));
    }

    public void save(Contact contact, Principal principal) {
        PrimaryUser primaryUser = primaryUserService.findPrimaryUserByPrincipal(principal);
        contact.setPrimaryUser(primaryUser);
        contactRepository.save(contact);
    }

    public void update(Contact contact, Principal principal) {

        PrimaryUser primaryUser = primaryUserService.findPrimaryUserByPrincipal(principal);
        contactRepository.findByIdAndPrimaryUser(contact.getId(), primaryUser)
                .map(contact1 -> {
                    return contactRepository.save(contact);
                }).orElseThrow(() -> new ResourceNotFoundException("Contact"));
    }


    public void deleteById(Long id, Principal principal) {
        Contact contact = findById(id, principal);
        contactRepository.delete(contact);
    }
}
