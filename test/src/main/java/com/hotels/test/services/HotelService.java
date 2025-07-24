package com.hotels.test.services;

import com.hotels.test.DTO.*;
import com.hotels.test.DTO.HotelSummaryDTO;
import com.hotels.test.entities.*;
import com.hotels.test.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

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

    public Map<String, Integer> histogramByParam(String param) {
        List<Hotel> hotels = hotelRepo.findAll();
        Map<String, Integer> histogram = new HashMap<>();
        switch (param.toLowerCase()) {
            case "brand":
                for (Hotel hotel : hotels) {
                    String brand = hotel.getBrand();
                    if (brand != null) {
                        histogram.put(brand, histogram.getOrDefault(brand, 0) + 1);
                    }
                }
                break;
            case "city":
                for (Hotel hotel : hotels) {
                    if (hotel.getAddress() != null) {
                        String city = hotel.getAddress().getCity();
                        if (city != null) {
                            histogram.put(city, histogram.getOrDefault(city, 0) + 1);
                        }
                    }
                }
                break;
            case "country":
                for (Hotel hotel : hotels) {
                    if (hotel.getAddress() != null) {
                        String country = hotel.getAddress().getCountry();
                        if (country != null) {
                            histogram.put(country, histogram.getOrDefault(country, 0) + 1);
                        }
                    }
                }
                break;
            case "amenities":
                for (Hotel hotel : hotels) {
                    if (hotel.getAminities() != null) {
                        for (String amenity : hotel.getAminities()) {
                            if (amenity != null) {
                                histogram.put(amenity, histogram.getOrDefault(amenity, 0) + 1);
                            }
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown parameter: " + param);
        }
        return histogram;
    }

    public void addAmenitiesToHotel(int id, List<String> amenities) {
        Optional<Hotel> hotelOpt = hotelRepo.findById(id);
        if (hotelOpt.isEmpty()) {
            throw new IllegalArgumentException("Hotel not found");
        }
        Hotel hotel = hotelOpt.get();
        if (hotel.getAminities() == null) {
            hotel.createAmenities(new Amenities());
        }
        for (String amenity : amenities) {
            if (!hotel.getAminities().contains(amenity)) {
                hotel.getAminities().add(amenity);
            }
        }
        hotelRepo.save(hotel);
    }

    public HotelSummaryDTO createHotel(HotelCreateRequest request){

        Hotel hotel = new Hotel();

        hotel.setName(request.name());
        hotel.setDescription(request.description());
        hotel.setBrand(request.brand());

        Address address = new Address();
        address.setHouseNumber(request.address().houseNumber());
        address.setStreet(request.address().street());
        address.setCity(request.address().city());
        address.setCountry(request.address().country());
        address.setPostCode(request.address().postCode());
        hotel.setAddress(address);

        // Преобразование контактов
        Contacts contact = new Contacts();
        contact.setPhone(request.contacts().phone());
        contact.setEmail(request.contacts().email());
        hotel.setContacts(contact);

        // Преобразование времени прибытия
        ArrivalTime arrivalTime = new ArrivalTime();
        arrivalTime.setCheckIn(request.arrivalTime().checkIn());
        arrivalTime.setCheckOut(request.arrivalTime().checkOut()); // Может быть null
        hotel.setArrivalTime(arrivalTime);


        hotelRepo.save(hotel);

        return getDTo(hotel);

    }




    public HotelSummaryDTO getDTo(Hotel hotel){

            HotelSummaryDTO hotelSummaryDTO = new HotelSummaryDTO(
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getDescription(),
                    hotel.getAddress().toString(),
                    hotel.getContacts().getPhone()
            );

        return hotelSummaryDTO;

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