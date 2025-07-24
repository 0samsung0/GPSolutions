package com.hotels.test.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
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


}
