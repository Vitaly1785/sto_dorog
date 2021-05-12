package ru.petukhov.sto_dorog.dto;

import lombok.Data;
import ru.petukhov.sto_dorog.entities.Role;

@Data
public class PersonDto {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String role;

}
