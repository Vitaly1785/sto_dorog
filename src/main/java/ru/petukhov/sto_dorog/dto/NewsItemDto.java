package ru.petukhov.sto_dorog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Data
public class NewsItemDto {
    private Long id;
    @NotBlank(message = "Поле \"Заголовок\" должно быть заполнено.")
    @Size(max = 255, message = "Введите не более 255 символов.")
    private String title;
    @NotBlank(message = "Поле \"Анонс\" должно быть заполнено.")
    @Size(max = 255, message = "Введите не более 255 символов.")
    private String anons;
    @NotBlank(message = "Поле \"Текст\" должно быть заполнено.")
    @Size(max = 5000, message = "Введите не более 5000 символов.")
    private String fullText;

    private String urlImage;
}
