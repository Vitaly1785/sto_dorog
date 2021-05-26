package ru.petukhov.sto_dorog.dto;

import lombok.Data;
import ru.petukhov.sto_dorog.Validate.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class PersonUpdateDto {
    private Long id;
    @NotBlank(message = "Поле \"Имя\" не заполнено")
    private String firstName;
    @NotBlank(message = "Поле \"Фамилия\" не заполнено")
    private String lastName;
    private String login;
    @NotBlank(message = "Не указан пароль")
    private String password;
    @Email(message = "Введите корректный адрес электронной почты")
    @NotEmpty(message = "Поле \"Email\" не заполнено")
    private String email;
    @NotBlank(message = "Поле \"Роль\" не заполнено")
    @Role
    private String role;
}
