package br.com.davidev.attusdesafiobackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "fullName", "dateOfBirth", "addressList"})
public record PersonDTO(
        Long id,
        @NotBlank(message = "Field not specified")
        @Pattern(regexp = "^[A-Z]+(.)*", message = "Name field must start with a capital letter")
        @JsonProperty("full_name")
        String fullName,
        @NotNull(message = "Date of birth cannot be null")
        @JsonProperty("date_birth")
        LocalDate dateOfBirth,
        @JsonProperty("address_list")
        List<AddressDTO> addressList) {
}
