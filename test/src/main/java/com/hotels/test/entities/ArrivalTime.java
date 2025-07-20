package com.hotels.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ArrivalTime {
    private Long id;
    private String checkIn;
    private String checkOut;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
