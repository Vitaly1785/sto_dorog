package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.NewsItem;


public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByNewsItem(NewsItem newsItem);
    Page<Comment> findAllByNewsItemOrderByTimeAsc(NewsItem newsItem, Pageable pageable);
    Page<Comment> findAllByNewsItemOrderByTimeDesc(NewsItem newsItem, Pageable pageable);
    Page<Comment> findAllByNewsItem(NewsItem newsItem, Pageable pageable);
}
