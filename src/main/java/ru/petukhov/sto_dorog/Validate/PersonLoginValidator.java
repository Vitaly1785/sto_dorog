package ru.petukhov.sto_dorog.Validate;

import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.services.PersonService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PersonLoginValidator implements ConstraintValidator<Login, String> {

    private final PersonService personService;

    public PersonLoginValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Person> optionalPerson = personService.findPersonByLogin(s);
        return !optionalPerson.isPresent();
    }
}
