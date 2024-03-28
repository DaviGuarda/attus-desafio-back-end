package br.com.davidev.attusdesafiobackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "streetAddress", "zipCode", "houseNumber", "city", "province", "defaultAddress", "person"})
public record AddressDTO(
        Long id,
        @NotBlank(message = "Field not specified")
        @JsonProperty("street_address")
        String streetAddress,
        @NotBlank(message = "Field not specified")
        @JsonProperty("zip_code")
        String zipCode,
        @NotNull(message = "Field not specified")
        @JsonProperty("house_number")
        int houseNumber,
        @NotBlank(message = "Field not specified")
        @Pattern(regexp = "^[A-Z]+(.)*", message = "City field must start with a capital letter")
        String city,
        @NotBlank(message = "Field not specified")
        @Pattern(regexp = "^[A-Z]+(.)*", message = "Province field must start with a capital letter")
        String province,
        Boolean defaultAddress) {
}
