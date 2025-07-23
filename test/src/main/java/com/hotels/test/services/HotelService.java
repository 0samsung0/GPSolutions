package com.hotels.test.services;

import com.hotels.test.DTO.HotelCreateDTO;
import com.hotels.test.DTO.HotelSummaryDTO;
import com.hotels.test.entities.Hotel;
import com.hotels.test.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    HotelRepo hotelRepo;

    @Autowired
    public HotelService(HotelRepo hotelRepo){
        this.hotelRepo = hotelRepo;
    }

    public List<HotelSummaryDTO> getAllHotels(){
        List<Hotel> hotels = hotelRepo.findAll();
        return getDTO(hotels);
    }

    public Optional<Hotel> getHotelById(int id){
        return hotelRepo.findById(id);
    }

    public List<HotelSummaryDTO> searchByArgument(String name, String brand, String city, String country, List<String> amenities) {
        List<Hotel> hotels = hotelRepo.findAll();
        List<Hotel> completeHotels = hotels.stream()
                .filter(hotel -> name == null || hotel.getName().equalsIgnoreCase(name))
                .filter(hotel -> brand == null || (hotel.getBrand() != null && hotel.getBrand().equalsIgnoreCase(brand)))
                .filter(hotel -> city == null || (hotel.getAddress() != null && hotel.getAddress().getCity().equalsIgnoreCase(city)))
                .filter(hotel -> country == null || (hotel.getAddress() != null && hotel.getAddress().getCountry().equalsIgnoreCase(country)))
                .toList();


        if(amenities.isEmpty()){
            return getDTO(completeHotels);
        }

        for(Hotel hotel : completeHotels) {
            if(hotel.getAminities().containsAll(amenities)){

            }
        }


        return getDTO(completeHotels
                .stream()
                .filter(hotel -> hotel.getAminities().containsAll(amenities))
                .toList());
    }


    public List<HotelSummaryDTO> getDTO(List<Hotel> hotelList){

        List<HotelSummaryDTO> hotelSummaryDTOList = new ArrayList<>();
        for(Hotel hotel : hotelList){
            HotelSummaryDTO hotelSummaryDTO = new HotelSummaryDTO(
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getDescription(),
                    hotel.getAddress().toString(),
                    hotel.getContacts().getPhone()
            );
            hotelSummaryDTOList.add(hotelSummaryDTO);
        }
        return hotelSummaryDTOList;

    }





} 