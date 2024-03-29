package br.com.davidev.attusdesafiobackend.config.unittests.converter;

import br.com.davidev.attusdesafiobackend.config.unittests.converter.mocks.MockPerson;
import br.com.davidev.attusdesafiobackend.model.converter.PersonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class PersonConverterTest {

    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    @Order(1)
    public void shouldParseEntityToDTO() {
        var personEntity = inputObject.mockEntity(1);
        var personDTO = PersonConverter.toDTO(personEntity);

        assertThat(personDTO.id()).isEqualTo(1L);
        assertThat(personDTO.fullName()).isEqualTo("David Test1");
        assertThat(personDTO.dateOfBirth()).isEqualTo(LocalDate.of(2003, 6, 10));
    }

    @Test
    @Order(2)
    public void shouldParseEntityListToDTOList(){
        var outPutListEntity = inputObject.mockEntityList();
        var outPutListDTO = PersonConverter.toDTOList(outPutListEntity);

        var outPutOne = outPutListDTO.get(1);

        assertThat(outPutOne.id()).isEqualTo(1L);
        assertThat(outPutOne.fullName()).isEqualTo("David Test1");
        assertThat(outPutOne.dateOfBirth()).isEqualTo(LocalDate.of(2003, 6, 10));

        var outPutFive = outPutListDTO.get(5);

        assertThat(outPutFive.id()).isEqualTo(5L);
        assertThat(outPutFive.fullName()).isEqualTo("David Test5");
        assertThat(outPutFive.dateOfBirth()).isEqualTo(LocalDate.of(2003, 6, 10));

        var outPutSeven = outPutListDTO.get(7);

        assertThat(outPutSeven.id()).isEqualTo(7L);
        assertThat(outPutSeven.fullName()).isEqualTo("David Test7");
        assertThat(outPutSeven.dateOfBirth()).isEqualTo(LocalDate.of(2003, 6, 10));
    }

    @Test
    @Order(3)
    public void shouldParseDTOToEntity(){
        var personDTO = inputObject.mockDTO(1);
        var personEntity = PersonConverter.toEntity(personDTO);

        assertThat(personEntity.getId()).isEqualTo(1L);
        assertThat(personEntity.getFullName()).isEqualTo("David Test1");
        assertThat(personEntity.getDateOfBirth()).isEqualTo(LocalDate.of(2003, 6, 10));
        assertThat(personEntity.getAddresses()).isEmpty();
    }
}
