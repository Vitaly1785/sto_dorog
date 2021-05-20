package ru.petukhov.sto_dorog.services;

import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.entities.Person;


public interface PersonService {
    Iterable<Person> findAll();

    Person findById(Long id);

    Person createPerson(PersonDto personDto);

    Person updatePerson(PersonDto personDto, Long id);

    void deletePerson(Long id);

    Person findByLogin(String login);

}
