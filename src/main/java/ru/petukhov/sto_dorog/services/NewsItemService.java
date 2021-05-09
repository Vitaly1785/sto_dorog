package ru.petukhov.sto_dorog.services;

import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;

public interface NewsItemService {
    Iterable<NewsItem> findAll();
    NewsItem findById(Long id);
    NewsItem createNewsItem(NewsItemDto newsItemDto);
    NewsItem updateNewsItem(NewsItemDto newsItemDto, Long id);
    void deleteNewsItem(Long id);
    boolean findNewsItem(Long id);
    void setViewsNewsItem(Long id);
}
