package br.com.davidev.attusdesafiobackend.model.converter;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PersonConverter {

    public static PersonDTO toDTO(Person entity) {
        return new PersonDTO(
                entity.getId(),
                entity.getFullName(),
                entity.getDateOfBirth(),
                entity.getAddresses() != null ? AddressConverter.toDTOList(entity.getAddresses()) : Collections.emptyList()
        );
    }

    public static Person toEntity(PersonDTO dto) {
        return Person.builder()
                .id(dto.id())
                .fullName(dto.fullName())
                .dateOfBirth(dto.dateOfBirth())
                .addresses(dto.addressList() != null ? AddressConverter.toEntityList(dto.addressList()) : null)
                .build();
    }

    public static List<PersonDTO> toDTOList(List<Person> entities) {
        return entities.stream()
                .map(PersonConverter::toDTO)
                .collect(Collectors.toList());
    }
}
