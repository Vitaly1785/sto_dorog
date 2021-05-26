package ru.petukhov.sto_dorog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {
    @NotBlank(message = "пустой комментарий")
    private String text;
}
