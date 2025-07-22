package com.hotels.test.services;

import com.hotels.test.entities.Hotel;
import com.hotels.test.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    HotelRepo hotelRepo;

    @Autowired
    public HotelService(HotelRepo hotelRepo){
        this.hotelRepo = hotelRepo;
    }

    public List<Hotel> getAllHotels(){
        return hotelRepo.findAll();
    }
} 