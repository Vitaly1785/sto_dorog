package ru.petukhov.sto_dorog.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.dto.PersonUpdateDto;
import ru.petukhov.sto_dorog.dto.UpdateByPersonDto;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.entities.Role;
import ru.petukhov.sto_dorog.exceptions.PersonNotFoundException;
import ru.petukhov.sto_dorog.exceptions.RoleNotFoundException;
import ru.petukhov.sto_dorog.repositories.PersonRepository;
import ru.petukhov.sto_dorog.repositories.RoleRepository;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AmazonClientService amazonClientService;

    public PersonServiceImpl(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AmazonClientService amazonClientService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.amazonClientService = amazonClientService;
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
    public Person createPerson(PersonDto personDto, MultipartFile multipartFile) {
        Person person = new Person().toBuilder().firstName(personDto.getFirstName()).lastName(personDto.getLastName())
                .login(personDto.getLogin()).password(passwordEncoder.encode(personDto.getPassword())).email(personDto.getEmail())
                .role(roleRepository.findByName("ROLE_USER").orElseThrow(()-> new RoleNotFoundException("Role not found")))
                .urlPhoto(getUrlUserPhoto(multipartFile)).build();
        personRepository.save(person);
        return person;
    }

    @Override
    public Person updateByPerson(UpdateByPersonDto personDto, String login, MultipartFile multipartFile) {
        Person person = findByLogin(login).toBuilder().firstName(personDto.getFirstName()).lastName(personDto.getLastName())
                .password(passwordEncoder.encode(personDto.getPassword())).email(personDto.getEmail())
                .urlPhoto(getUrlUserPhoto(multipartFile)).build();
        personRepository.save(person);
        return person;
    }

    @Override
    public Person updatePerson(PersonUpdateDto personDto, String login) {
        Person person = findByLogin(login).toBuilder().firstName(personDto.getFirstName()).lastName(personDto.getLastName())
                .password(passwordEncoder.encode(personDto.getPassword())).email(personDto.getEmail()).role(getRole(personDto))
                .build();
        personRepository.save(person);
        return person;
    }

    @Override
    public void deletePerson(String login) {
        amazonClientService.deleteFileFromS3Bucket(findByLogin(login).getUrlPhoto());
        personRepository.delete(findByLogin(login));
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(()-> new PersonNotFoundException("Person not found"));
    }


    @Override
    public Optional<Person> findPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    public Role getRole(PersonUpdateDto personDto){
        return roleRepository.findByName(personDto.getRole())
                .orElseThrow(()-> new RoleNotFoundException("Role not found"));
    }

    public String getUrlUserPhoto(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return "https://kmto-premiera.ru/upload/speaker-azzurro.png";
        }else {
            return amazonClientService.uploadFile(multipartFile);
        }
    }
}
