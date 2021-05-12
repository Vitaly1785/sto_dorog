package ru.petukhov.sto_dorog.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ErrorDto {
    private String message;

    public ErrorDto(String message) {
        this.message = message;
    }
}
