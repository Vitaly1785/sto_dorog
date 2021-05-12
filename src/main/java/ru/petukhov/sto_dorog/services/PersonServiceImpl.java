package ru.petukhov.sto_dorog.services;

import org.springframework.stereotype.Service;
import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.exceptions.PersonNotFoundException;
import ru.petukhov.sto_dorog.exceptions.RoleNotFoundException;
import ru.petukhov.sto_dorog.repositories.PersonRepository;
import ru.petukhov.sto_dorog.repositories.RoleRepository;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException("Person not found"));
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        Optional<Person> optionalPerson = personRepository.findByLogin(personDto.getLogin());
        if (optionalPerson.isPresent()){
            return person;
        }
        person.setLogin(personDto.getLogin());
        person.setPassword("{noop}" + personDto.getPassword());
        person.setEmail(personDto.getEmail());
        person.setRole(roleRepository.findByName("ROLE_USER").orElseThrow(()-> new RoleNotFoundException("Role not found")));
        personRepository.save(person);
        return person;
    }

    @Override
    public Person updatePerson(PersonDto personDto, Long id) {
        Person person = findById(id);
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPassword(personDto.getPassword());
        person.setEmail(personDto.getEmail());
        person.setRole(roleRepository.findByName(personDto.getRole()).orElse(roleRepository.findByName("ROLE_USER")
                .orElseThrow(()-> new RoleNotFoundException("Role not found"))));
        personRepository.save(person);
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(findById(id));
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(()-> new PersonNotFoundException("Person not found"));
    }
}
