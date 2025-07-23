package com.hotels.test.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Amenities {

    @Id
    private Long id;


    @ElementCollection
    private List<String> amenities;


    public List<String> getAmenities(){
        return (List<String>) amenities.stream().sorted();
    }
}
