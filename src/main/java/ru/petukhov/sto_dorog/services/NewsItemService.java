package ru.petukhov.sto_dorog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;

import java.security.Principal;

public interface NewsItemService {
    Iterable<NewsItem> findAll();

    NewsItem findById(Long id);

    NewsItem createNewsItem(NewsItemDto newsItemDto, Principal principal);

    NewsItem updateNewsItem(NewsItemDto newsItemDto, Long id);

    void deleteNewsItem(Long id);

    boolean findNewsItem(Long id);

    void setViewsNewsItem(Long id);

    Page<NewsItem> getRecentNews(Pageable pageable);

    Page<NewsItem> getOldNews(Pageable pageable);

    Page<NewsItem> getNews(Pageable pageable);

    NewsItem getLatestNews(Pageable pageable);
}
