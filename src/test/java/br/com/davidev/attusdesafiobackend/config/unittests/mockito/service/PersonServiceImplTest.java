package br.com.davidev.attusdesafiobackend.config.unittests.mockito.service;

import br.com.davidev.attusdesafiobackend.config.unittests.converter.mocks.MockPerson;
import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.exception.ResourceNotFoundException;
import br.com.davidev.attusdesafiobackend.model.Person;
import br.com.davidev.attusdesafiobackend.model.converter.PersonConverter;
import br.com.davidev.attusdesafiobackend.repository.PersonRepository;
import br.com.davidev.attusdesafiobackend.service.implementation.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest {

    MockPerson input;
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setup() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreatePerson() {
        var personDTO = input.mockDTO(1);

        var person = PersonConverter.toEntity(personDTO);
        var savedPerson = input.mockEntity(1);

        when(personRepository.save(person)).thenReturn(savedPerson);

        var result = personService.create(personDTO);

        verify(personRepository, times(1)).save(person);
        assertThat(savedPerson.getId()).isEqualTo(result.id());
        assertThat(savedPerson.getFullName()).isEqualTo(result.fullName());
        assertThat(savedPerson.getDateOfBirth()).isEqualTo(result.dateOfBirth());
    }

    @Test
    public void shouldUpdatePerson() {
        var personDTO = input.mockDTO(1);

        var person = input.mockEntity(1);

        var updatedPerson = input.mockEntity(1);
        updatedPerson.setFullName("David G");

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(updatedPerson);

        var result = personService.update(1L, personDTO);

        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).save(person);
        assertThat(updatedPerson.getId()).isEqualTo(result.id());
        assertThat(updatedPerson.getFullName()).isEqualTo(result.fullName());
        assertThat(updatedPerson.getDateOfBirth()).isEqualTo(result.dateOfBirth());
    }

    @Test
    public void whenUpdateThrowsResourceNotFoundException() {
        Long id = 1L;
        var personDTO = new PersonDTO(null, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.update(id, personDTO));
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, never()).save(any());
    }

    @Test
    public void shouldFindByIdPerson() {
        var person = input.mockEntity(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = personService.findById(1L);

        verify(personRepository, times(1)).findById(1L);
        assertThat(person.getId()).isEqualTo(result.id());
        assertThat(person.getFullName()).isEqualTo(result.fullName());
        assertThat(person.getDateOfBirth()).isEqualTo(result.dateOfBirth());
    }

    @Test
    public void whenFindByIdPersonThrowsResourceNotFoundException() {
        Long id = 1L;

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.findById(id));
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllPeople() {
        List<Person> personList = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(personList);

        List<PersonDTO> result = personService.findAll();

        verify(personRepository, times(1)).findAll();
        assertThat(personList.size()).isEqualTo(result.size());
        assertThat(personList.get(0).getId()).isEqualTo(result.get(0).id());
        assertThat(personList.get(0).getFullName()).isEqualTo(result.get(0).fullName());
        assertThat(personList.get(0).getDateOfBirth()).isEqualTo(result.get(0).dateOfBirth());
        assertThat(personList.get(1).getId()).isEqualTo(result.get(1).id());
        assertThat(personList.get(1).getFullName()).isEqualTo(result.get(1).fullName());
        assertThat(personList.get(1).getDateOfBirth()).isEqualTo(result.get(1).dateOfBirth());
    }

    @Test
    public void shouldDeletePerson() {
        var person = input.mockEntity(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        personService.delete(1L);

        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).delete(person);
    }

    @Test
    public void whenDeleteThrowsResourceNotFoundException() {
        Long id = 1L;

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personService.delete(id));
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, never()).delete(any());
    }
}