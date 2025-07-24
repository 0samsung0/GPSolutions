package com.hotels.test.DTO;

public record HotelSummaryResponse(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {}