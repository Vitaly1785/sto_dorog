package ru.petukhov.sto_dorog.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.NewsItem;

import java.util.List;

public interface NewsItemRepository extends CrudRepository<NewsItem, Long> {
    Page<NewsItem> findAllByOrderByTimeAsc(Pageable pageable);

    Page<NewsItem> findAllByOrderByTimeDesc(Pageable pageable);

    Page<NewsItem> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"comments"})
    List<NewsItem> findAll();
}
