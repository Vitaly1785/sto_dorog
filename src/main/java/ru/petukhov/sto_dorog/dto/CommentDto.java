package ru.petukhov.sto_dorog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    @NotBlank(message = "пустой комментарий")
    @Size(max = 510, message = "Введите не более 510 символов.")
    private String text;
}
