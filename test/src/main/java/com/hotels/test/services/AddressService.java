package com.hotels.test.services;


import com.hotels.test.repositories.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepos){
        this.addressRepo = addressRepos;
    }
}
