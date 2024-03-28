package br.com.davidev.attusdesafiobackend.service.implementation;

import br.com.davidev.attusdesafiobackend.dto.PersonDTO;
import br.com.davidev.attusdesafiobackend.model.Person;
import br.com.davidev.attusdesafiobackend.model.converter.PersonConverter;
import br.com.davidev.attusdesafiobackend.repository.PersonRepository;
import br.com.davidev.attusdesafiobackend.service.PersonService;
import br.com.davidev.attusdesafiobackend.util.ExceptionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonDTO create(PersonDTO data) {
        var person = PersonConverter.toEntity(data);
        var savedPerson = personRepository.save(person);

        log.info("[PersonServiceImpl:personCreate] Create one person:{}, has been created", savedPerson);

        return PersonConverter.toDTO(savedPerson);
    }

    @Override
    public PersonDTO update(Long id, PersonDTO data) {

        var person = personRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[PersonServiceImpl:personUpdate] Person with Id :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        person.setFullName(data.fullName());
        person.setDateOfBirth(data.dateOfBirth());

        var updatedPerson = personRepository.save(person);
        log.info("[PersonServiceImpl:personUpdate] Update one person:{}, has been updated", updatedPerson);

        return PersonConverter.toDTO(updatedPerson);
    }

    @Override
    public PersonDTO findById(Long id) {

        var person = personRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[PersonServiceImpl:personUpdate] Person with Id :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        log.info("[PersonServiceImpl:personFindById] Getting a person:{}, person found", person);

        return PersonConverter.toDTO(person);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();

        log.info("[PersonServiceImpl:personFindAll] Getting all persons:{}, persons found", personList);

        return PersonConverter.toDTOList(personList);
    }

    @Override
    public void delete(Long id) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[PersonServiceImpl:personUpdate] Person with Id :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        personRepository.delete(person);
        log.info("[PersonServiceImpl:personDelete] Delete one person:{}, person deleted", person);
    }

}
