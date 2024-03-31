package br.com.davidev.attusdesafiobackend.controller;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/person/v1")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO data) {
        var person = personService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable(value = "id") Long id, @RequestBody @Valid PersonDTO data) {
        var person = personService.update(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable(value = "id") Long id) {
        var person = personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        var people = personService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok("Person deleted successfully!");
    }
}
