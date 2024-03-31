package br.com.davidev.attusdesafiobackend.config.unittests.mockito.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.davidev.attusdesafiobackend.controller.PersonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.service.PersonService;

public class PersonControllerTest {

    private PersonService personService;
    private PersonController personController;

    @BeforeEach
    public void setUp() {
        personService = mock(PersonService.class);
        personController = new PersonController(personService);
    }

    @Test
    public void shouldCreatePerson() {
        PersonDTO personDTO = new PersonDTO(null, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());

        PersonDTO createdPerson = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());

        when(personService.create(personDTO)).thenReturn(createdPerson);

        ResponseEntity<PersonDTO> response = personController.create(personDTO);

        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
        assertThat(createdPerson).isEqualTo(response.getBody());
        verify(personService, times(1)).create(personDTO);
    }

    @Test
    public void shouldUpdatePerson() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO(null, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());

        PersonDTO updatedPerson = new PersonDTO(id, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());

        when(personService.update(id, personDTO)).thenReturn(updatedPerson);

        ResponseEntity<PersonDTO> response = personController.update(id, personDTO);

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(updatedPerson).isEqualTo(response.getBody());
        verify(personService, times(1)).update(id, personDTO);
    }

    @Test
    public void shouldFindByIdPerson() {
        Long id = 1L;
        PersonDTO personDTO = new PersonDTO(id, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());

        when(personService.findById(id)).thenReturn(personDTO);

        ResponseEntity<PersonDTO> response = personController.findById(id);

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(personDTO).isEqualTo(response.getBody());
        verify(personService, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllPeople() {
        List<PersonDTO> people = new ArrayList<>();
        PersonDTO person1 = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6,10), Collections.emptyList());
        people.add(person1);
        PersonDTO person2 = new PersonDTO(2L, "Jane Smith", LocalDate.of(2003, 6,10), Collections.emptyList());
        people.add(person2);

        when(personService.findAll()).thenReturn(people);

        ResponseEntity<List<PersonDTO>> response = personController.findAll();

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(people).isEqualTo(response.getBody());
        verify(personService, times(1)).findAll();
    }

    @Test
    public void shouldDeletePerson() {
        Long id = 1L;

        ResponseEntity<?> response = personController.delete(id);

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat("Person deleted successfully!").isEqualTo(response.getBody());
        verify(personService, times(1)).delete(id);
    }
}