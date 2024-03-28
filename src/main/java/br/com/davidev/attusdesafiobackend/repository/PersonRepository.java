package br.com.davidev.attusdesafiobackend.repository;

import br.com.davidev.attusdesafiobackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
