package br.com.davidev.attusdesafiobackend.service;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    PersonDTO create (PersonDTO data);
    PersonDTO update (Long id, PersonDTO data);
    PersonDTO findById (Long id);
    List<PersonDTO> findAll();
    void delete (Long id);
}
