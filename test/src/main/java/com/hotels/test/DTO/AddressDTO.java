package com.hotels.test.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(
        @NotNull Integer houseNumber,
        @NotBlank String street,
        @NotBlank String city,
        @NotBlank String country,
        String postCode
) {}