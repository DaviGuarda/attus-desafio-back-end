package br.com.davidev.attusdesafiobackend.config.unittests.converter.mocks;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.model.Person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Person> mockEntityList() {
        return IntStream.range(0, 10).mapToObj(this::mockEntity).collect(Collectors.toList());
    }

    public List<PersonDTO> mockDTOList() {
        return IntStream.range(0, 10).mapToObj(this::mockDTO).collect(Collectors.toList());
    }

    public Person mockEntity(Integer number) {
        return Person.builder()
                .id(number.longValue())
                .fullName("David Test" + number)
                .dateOfBirth(LocalDate.of(2003, 6, 10))
                .addresses(Collections.emptyList())
                .build();
    }

    public PersonDTO mockDTO(Integer number) {
        return new PersonDTO(
                number.longValue(),
                "David Test" + number,
                LocalDate.of(2003, 6, 10),
                Collections.emptyList()
        );
    }

}
