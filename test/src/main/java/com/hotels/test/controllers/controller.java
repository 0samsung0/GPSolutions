package com.hotels.test.controllers;


import com.hotels.test.DTO.HotelCreateDTO;
import com.hotels.test.DTO.HotelSummaryDTO;
import com.hotels.test.entities.Hotel;
import com.hotels.test.services.HotelService;
import com.hotels.test.services.AmenetiesService;
import com.hotels.test.services.AddressService;
import com.hotels.test.services.ArrivalTimeService;
import com.hotels.test.services.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    @GetMapping(value="hotels")/////////////////////////
    public ResponseEntity<List<HotelSummaryDTO>> hotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }


    @GetMapping(value = "/{id}")/////////////////////////
    public ResponseEntity<Hotel> getInfo(
            @PathVariable("id") int id
    ){
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel.get());
    }


    @GetMapping(value = "/search")/////////////////////////
    public ResponseEntity<List<HotelSummaryDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities
    ){
        List<HotelSummaryDTO> hotels = hotelService.searchByArgument(name, brand, city, country, (List<String>) amenities.stream().sorted());
        return ResponseEntity.ok(hotels);
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
        HotelSummaryDTO hotelSummaryDTO = hotelService.createHotel(hotelCreateDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelSummaryDTO);

    }


    @PostMapping(value = "/{id}/amenities")
    public ResponseEntity<Void> addAmenities(
            @PathVariable int id,
            @RequestBody List<String> amenities
    ){

    }


}
