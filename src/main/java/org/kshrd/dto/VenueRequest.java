package org.kshrd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenueRequest {

    @NotNull(message = "Name must not be blank")
    @NotBlank(message = "Name must not be blank")
    private String venueName;

    @NotNull(message = "Location must not be blank")
    @NotBlank(message = "Location must not be blank")
    private String location;
}
