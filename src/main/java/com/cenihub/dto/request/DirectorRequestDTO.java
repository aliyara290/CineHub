package com.cenihub.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

public record DirectorRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @Size(max = 50, message = "Nationality must not exceed 50 characters")
        String nationality,

        @Min(value = 1920, message = "Birth year must be after 1920")
        @Max(value = 2024, message = "Birth year must not exceed 2024")
        Integer birthYear
) {
}
