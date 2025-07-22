package com.hotels.test.DTO;

import com.hotels.test.entities.Address;
import com.hotels.test.entities.ArrivalTime;
import com.hotels.test.entities.Contacts;

public class HotelCreateDTO {

    public record HotelCreateDTOs(
            String name,
            String description,
            String brand,
            Address address,
            Contacts contacts,
            ArrivalTime arrivalTime
    ) {}
}
