package com.hotels.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hotel {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
