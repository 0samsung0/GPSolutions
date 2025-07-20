package com.hotels.test.services;

import com.hotels.test.repositories.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {
    ContactsRepo contactsRepo;

    @Autowired
    public ContactsService(ContactsRepo contactsRepo){
        this.contactsRepo = contactsRepo;
    }
} 