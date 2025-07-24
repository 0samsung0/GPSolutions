package com.hotels.test.DTO;

import com.hotels.test.entities.Address;
import com.hotels.test.entities.ArrivalTime;
import com.hotels.test.entities.Contacts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


public record HotelCreateRequest(
        @NotBlank String name,
        String description,          // Опциональное поле
        @NotBlank String brand,
        @Valid AddressDTO address,
        @Valid ContactDTO contacts,
        @Valid ArrivalTimeDTO arrivalTime
) {}
