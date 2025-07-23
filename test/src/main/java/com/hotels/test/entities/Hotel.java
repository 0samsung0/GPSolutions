package com.hotels.test.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "contacts")
    private Contacts contacts;

    @OneToOne
    @JoinColumn
    private ArrivalTime arrivalTime;

    @OneToOne
    @JoinColumn
    private Amenities amenities;

    @OneToOne
    @JoinColumn
    private Address address;

    @Column
    private String description;

    @Column
    private String brand;

    @Column
    private String name;



    public List<String> getAminities(){
        return amenities.getAmenities();
    }


}
