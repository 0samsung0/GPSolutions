package com.hotels.test.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String phone;
    @Column
    private String email;


}
