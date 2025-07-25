package com.hotels.test.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @ElementCollection(fetch = FetchType.LAZY) // Eager по умолчанию может быть проблематичен
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity") // Имя колонки для каждого элемента в списке
    private List<String> amenities = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;

    private String description;

    private String brand;

    @Column
    private String name;



    public List<String> getAmenities(){
        if (amenities == null) return new java.util.ArrayList<>();
        return amenities;
    }

//    @Autowired
//    public void createAmenities(Amenities amenities){
//        this.amenities = amenities;
//    }

}
