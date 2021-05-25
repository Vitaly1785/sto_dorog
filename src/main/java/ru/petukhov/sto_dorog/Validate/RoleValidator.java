package ru.petukhov.sto_dorog.Validate;

import ru.petukhov.sto_dorog.repositories.RoleRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class RoleValidator implements ConstraintValidator<Role, String> {

    private final RoleRepository roleRepository;

    public RoleValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ru.petukhov.sto_dorog.entities.Role> role = roleRepository.findByName(s);
        return role.isPresent();
    }
}
