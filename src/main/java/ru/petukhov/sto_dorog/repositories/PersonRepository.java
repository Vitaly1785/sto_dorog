package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
        Optional<Person> findByLogin(String login);
}
