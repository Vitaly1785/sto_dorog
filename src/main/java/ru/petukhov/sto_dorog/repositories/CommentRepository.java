package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
