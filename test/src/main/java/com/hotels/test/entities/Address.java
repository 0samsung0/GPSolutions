package com.hotels.test.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    private Integer houseNumber;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String country;

    @Column
    private String postCode;



    public String toString(){

        return houseNumber + " "
                +street+", "
                +city + ", "
                +postCode+", "
                +country;
    }
}
