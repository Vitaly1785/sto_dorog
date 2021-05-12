package ru.petukhov.sto_dorog.exceptions;

public class NewsItemNotFoundException extends NotFoundException{
    public NewsItemNotFoundException(String message) {
        super(message);
    }
}
