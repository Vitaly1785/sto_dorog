package ru.petukhov.sto_dorog.services;

import org.springframework.stereotype.Service;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.exceptions.NotFoundException;
import ru.petukhov.sto_dorog.repositories.CommentRepository;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> findAllById(Iterable<Long> id) {
        return commentRepository.findAllById(id);
    }

    @Override
    public Comment createComment(Comment comment, Person person) {
        comment.setPerson(person);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(findById(id).orElseThrow(()-> new NotFoundException("not found")));
    }
}
