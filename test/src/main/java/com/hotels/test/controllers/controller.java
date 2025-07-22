package com.hotels.test.controllers;


import com.hotels.test.DTO.HotelCreateDTO;
import com.hotels.test.entities.Hotel;
import com.hotels.test.services.HotelService;
import com.hotels.test.services.AmenetiesService;
import com.hotels.test.services.AddressService;
import com.hotels.test.services.ArrivalTimeService;
import com.hotels.test.services.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
public class controller {
    private final HotelService hotelService;
    private final AmenetiesService amenetiesService;
    private final AddressService addressService;
    private final ArrivalTimeService arrivalTimeService;
    private final ContactsService contactsService;

    @Autowired
    public controller(HotelService hotelService,
                      AmenetiesService amenetiesService,
                      AddressService addressService,
                      ArrivalTimeService arrivalTimeService,
                      ContactsService contactsService) {
        this.hotelService = hotelService;
        this.amenetiesService = amenetiesService;
        this.addressService = addressService;
        this.arrivalTimeService = arrivalTimeService;
        this.contactsService = contactsService;
    }


    @GetMapping(value="hotels")
    public ResponseEntity<List<Hotel>> hotels(){
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> getInfo(
            @PathVariable("id") int id
    ){
        Hotel hotel = hotelService.getAllHotels().get(id);
        return ResponseEntity.ok(hotel);
    }


    @GetMapping(value = "/search")
    public ResponseEntity<List<Hotel>> search(){

    }


    @GetMapping(value = "/histogram/{param}")
    public ResponseEntity<Map<String, Integer>> histogram(
            @PathVariable("param") String param
    ){

    }


    @PostMapping(value = "/hotels")
    public ResponseEntity createHotel(
            @RequestBody HotelCreateDTO hotelCreateDTOs
            ){


    }


    @PostMapping(value = "/{id}/amenities")
    public ResponseEntity<Void> addAmenities(
            @PathVariable int id,
            @RequestBody List<String> amenities
    ){
        
    }


}
