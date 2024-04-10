package br.com.davidev.attusdesafiobackend.unittests.mockito.controller;

import br.com.davidev.attusdesafiobackend.controller.PersonController;
import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldCreatePerson() throws Exception {
        var person = new PersonDTO(null, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());
        var createdPerson = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());


        when(personService.create(person)).thenReturn(createdPerson);

        mockMvc.perform(post("/api/person/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.full_name").value("John Doe"))
                .andExpect(jsonPath("$.date_birth").value("2003-06-10"));

        verify(personService, times(1)).create(person);
    }

    @Test
    public void shouldReturnBadRequestWhenPersonDtoIsInvalid() throws Exception {
        var invalidPerson = new PersonDTO(null, "name invalid", LocalDate.of(2003, 6, 10), Collections.emptyList());

        String invalidPersonJson = objectMapper.writeValueAsString(invalidPerson);

        mockMvc.perform(post("/api/person/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPersonJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[fullName: Name field must start with a capital letter]"));
    }

    @Test
    public void shouldUpdatePerson() throws Exception {
        var person = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());

        var updatedPerson = new PersonDTO(1L, "John Dev", LocalDate.of(2003, 6, 10), Collections.emptyList());

        when(personService.update(1L, person)).thenReturn(updatedPerson);

        mockMvc.perform(put("/api/person/v1/{id}", person.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.full_name").value("John Dev"))
                .andExpect(jsonPath("$.date_birth").value("2003-06-10"));

        verify(personService, times(1)).update(1L, person);
    }

    @Test
    public void shouldFindByIdPerson() throws Exception {
        var person = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());

        when(personService.findById(1L)).thenReturn(person);

        mockMvc.perform(get("/api/person/v1/{id}", person.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.full_name").value("John Doe"))
                .andExpect(jsonPath("$.date_birth").value("2003-06-10"));

        verify(personService, times(1)).findById(1L);
    }

    @Test
    public void shouldFindAllPeople() throws Exception {
        List<PersonDTO> people = new ArrayList<>();
        var person1 = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());
        people.add(person1);
        var person2 = new PersonDTO(2L, "Jane Smith", LocalDate.of(2003, 6, 10), Collections.emptyList());
        people.add(person2);

        when(personService.findAll()).thenReturn(people);

        mockMvc.perform(get("/api/person/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].full_name").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].full_name").value("Jane Smith"));

        verify(personService, times(1)).findAll();
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        var person = new PersonDTO(1L, "John Doe", LocalDate.of(2003, 6, 10), Collections.emptyList());

        mockMvc.perform(delete("/api/person/v1/{id}", person.id()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Person deleted successfully!")));

        verify(personService, times(1)).delete(1L);
    }
}