package com.hotels.test.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactDTO(
        @NotBlank String phone,
        @Email String email
) {}