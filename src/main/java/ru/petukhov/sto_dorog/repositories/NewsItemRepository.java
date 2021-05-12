package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.NewsItem;

public interface NewsItemRepository extends CrudRepository<NewsItem, Long> {

}
