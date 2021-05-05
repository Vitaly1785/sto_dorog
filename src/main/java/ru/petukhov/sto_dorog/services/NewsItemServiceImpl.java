package ru.petukhov.sto_dorog.services;

import org.springframework.stereotype.Service;
import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.exceptions.NewsItemNotFoundException;
import ru.petukhov.sto_dorog.repositories.NewsItemRepository;

import java.time.LocalDateTime;


@Service
public class NewsItemServiceImpl implements NewsItemService{
    private final NewsItemRepository newsItemRepository;

    public NewsItemServiceImpl(NewsItemRepository newsItemRepository) {
        this.newsItemRepository = newsItemRepository;
    }

    @Override
    public Iterable<NewsItem> findAll() {
        return newsItemRepository.findAll();
    }

    @Override
    public NewsItem findById(Long id) {
      NewsItem newsItem = newsItemRepository.findById(id).orElseThrow(()-> new NewsItemNotFoundException(String.format("NewsItem with %s id not found", id)));
      int count = newsItem.getViews();
      newsItem.setViews(++count);
      newsItemRepository.save(newsItem);
      return newsItem;
    }

    @Override
    public NewsItem createNewsItem(NewsItemDto newsItemDto) {
        NewsItem newsItem = new NewsItem();
        newsItem.setTitle(newsItemDto.getTitle());
        newsItem.setAnons(newsItemDto.getAnons());
        newsItem.setFullText(newsItemDto.getFullText());
        newsItem.setTime(LocalDateTime.now());
        newsItemRepository.save(newsItem);
        return newsItem;
    }

    @Override
    public NewsItem updateNewsItem(NewsItemDto newsItemDto, Long id) {
        NewsItem newsItem = findById(id);
        newsItem.setTitle(newsItemDto.getTitle());
        newsItem.setAnons(newsItemDto.getAnons());
        newsItem.setFullText(newsItemDto.getFullText());
        newsItem.setTime(LocalDateTime.now());
        newsItemRepository.save(newsItem);
        return newsItem;
    }

    @Override
    public void deleteNewsItem(Long id) {
        newsItemRepository.delete(findById(id));
    }

    @Override
    public boolean findNewsItem(Long id){
        return newsItemRepository.existsById(id);
    }
}
