package com.hotels.test.repositories;


import com.hotels.test.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepo extends JpaRepository<Contacts, Integer> {
}
