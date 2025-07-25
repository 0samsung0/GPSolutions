package com.hotels.test.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts")
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ArrivalTime arrivalTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Amenities amenities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    private String description;

    private String brand;

    @Column
    private String name;



    public List<String> getAminities(){
        if (amenities == null) return new java.util.ArrayList<>();
        return amenities.getAmenities();
    }

    @Autowired
    public void createAmenities(Amenities amenities){
        this.amenities = amenities;
    }

}
