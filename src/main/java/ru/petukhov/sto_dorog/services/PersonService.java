package ru.petukhov.sto_dorog.services;

import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.dto.PersonUpdateDto;
import ru.petukhov.sto_dorog.dto.UpdateByPersonDto;
import ru.petukhov.sto_dorog.entities.Person;

import java.util.Optional;


public interface PersonService {
    Iterable<Person> findAll();

    Person findById(Long id);

    Person createPerson(PersonDto personDto);

    Person updateByPerson(UpdateByPersonDto personDto, String login);

    Person updatePerson(PersonUpdateDto personDto, String login);

    void deletePerson(String login);

    Person findByLogin(String login);

    Optional<Person> findPersonByLogin(String login);

}
