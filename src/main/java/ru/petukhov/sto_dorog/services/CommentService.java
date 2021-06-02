package ru.petukhov.sto_dorog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.petukhov.sto_dorog.dto.CommentDto;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.NewsItem;

import java.security.Principal;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);

    Iterable<Comment> findAll(NewsItem newsItem);

    Comment createComment(CommentDto comment, Principal principal, Long id);

    void deleteComment(Long id);


    Page<Comment> findOldComments(NewsItem newsItem, Pageable pageable);

    Page<Comment> findRecentComment(NewsItem newsItem, Pageable pageable);

    Page<Comment> findAllComments(NewsItem newsItem, Pageable pageable);
}
