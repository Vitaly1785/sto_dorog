package ru.petukhov.sto_dorog.exceptions;

public class PersonNotFoundException extends NotFoundException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
