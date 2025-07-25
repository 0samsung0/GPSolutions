//package com.hotels.test.entities;
//
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class Amenities {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @ElementCollection
//    private List<String> amenities = new ArrayList<>();
//
//
//    public List<String> getAmenities(){
//        if (amenities == null) amenities = new ArrayList<>();
//        return new ArrayList<>(amenities.stream().sorted().toList());
//    }
//}
