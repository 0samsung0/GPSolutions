package com.hotels.test.DTO;


public record HotelSummaryDTO(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {}
