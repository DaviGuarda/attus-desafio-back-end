package br.com.davidev.attusdesafiobackend.model.converter;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressConverter {

    public static AddressDTO toDTO(Address entity) {
        return new AddressDTO(
                entity.getId(),
                entity.getStreetAddress(),
                entity.getZipCode(),
                entity.getHouseNumber(),
                entity.getCity(),
                entity.getProvince(),
                entity.getDefaultAddress()
        );
    }

    public static Address toEntity(AddressDTO dto) {
        return Address.builder()
                .id(dto.id())
                .streetAddress(dto.streetAddress())
                .zipCode(dto.zipCode())
                .houseNumber(dto.houseNumber())
                .city(dto.city())
                .province(dto.province())
                .defaultAddress(dto.defaultAddress())
                .build();
    }

    public static List<AddressDTO> toDTOList(List<Address> entities) {
        return entities.stream()
                .map(AddressConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Address> toEntityList(List<AddressDTO> addressDTOS) {
        return addressDTOS.stream()
                .map(AddressConverter::toEntity)
                .collect(Collectors.toList());
    }
}

