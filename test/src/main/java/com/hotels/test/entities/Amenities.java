package com.hotels.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import jakarta.persistence.ElementCollection;

@Entity
public class Amenities {
    private Long id;
    @ElementCollection
    private List<String> amenities;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}
