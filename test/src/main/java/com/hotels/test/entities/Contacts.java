package com.hotels.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Contacts {
    private Long id;
    private String phone;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
