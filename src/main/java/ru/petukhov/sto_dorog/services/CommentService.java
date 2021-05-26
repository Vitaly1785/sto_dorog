package ru.petukhov.sto_dorog.services;

import ru.petukhov.sto_dorog.dto.CommentDto;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.Person;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);

    Iterable<Comment> findAllById(Iterable<Long> id);

    Comment createComment(Comment comment, Person person);

    void deleteComment(Long id);
}
