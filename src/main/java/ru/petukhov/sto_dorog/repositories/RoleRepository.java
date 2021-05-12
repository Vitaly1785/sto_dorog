package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
        Optional<Role> findByName(String name);
}
