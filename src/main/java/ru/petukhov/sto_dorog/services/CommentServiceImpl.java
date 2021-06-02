package ru.petukhov.sto_dorog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.petukhov.sto_dorog.dto.CommentDto;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.exceptions.NewsItemNotFoundException;
import ru.petukhov.sto_dorog.exceptions.NotFoundException;
import ru.petukhov.sto_dorog.exceptions.PersonNotFoundException;
import ru.petukhov.sto_dorog.repositories.CommentRepository;
import ru.petukhov.sto_dorog.repositories.NewsItemRepository;
import ru.petukhov.sto_dorog.repositories.PersonRepository;

import java.security.Principal;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final NewsItemRepository newsItemRepository;
    private final PersonRepository personRepository;

    public CommentServiceImpl(CommentRepository commentRepository, NewsItemRepository newsItemRepository, PersonRepository personRepository) {
        this.commentRepository = commentRepository;
        this.newsItemRepository = newsItemRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Iterable<Comment> findAll(NewsItem newsItem) {
        return commentRepository.findAllByNewsItem(newsItem);
    }


    @Override
    public Comment createComment(CommentDto commentDto, Principal principal, Long id) {
        Comment comment = new Comment();
        comment.setPerson(personRepository.findByLogin(principal.getName())
                .orElseThrow(()-> new PersonNotFoundException("Person Not Found")));
        comment.setNewsItem(newsItemRepository.findById(id)
                .orElseThrow(()-> new NewsItemNotFoundException("NewsItem Not Found")));
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(findById(id).orElseThrow(()-> new NotFoundException("not found")));
    }


    @Override
    public Page<Comment> findOldComments(NewsItem newsItem, Pageable pageable) {
        return commentRepository.findAllByNewsItemOrderByTimeAsc(newsItem, pageable);
    }

    @Override
    public Page<Comment> findRecentComment(NewsItem newsItem, Pageable pageable) {
        return commentRepository.findAllByNewsItemOrderByTimeDesc(newsItem, pageable);
    }

    @Override
    public Page<Comment> findAllComments(NewsItem newsItem, Pageable pageable) {
        return commentRepository.findAllByNewsItem(newsItem, pageable);
    }
}
