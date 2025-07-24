package com.hotels.test.DTO;

import jakarta.validation.constraints.NotBlank;

public record ArrivalTimeDTO(
        @NotBlank String checkIn,
        String checkOut             // Опциональное поле
) {}