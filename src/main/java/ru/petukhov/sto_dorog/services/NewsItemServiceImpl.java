package ru.petukhov.sto_dorog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.exceptions.NewsItemNotFoundException;
import ru.petukhov.sto_dorog.exceptions.PersonNotFoundException;
import ru.petukhov.sto_dorog.repositories.NewsItemRepository;
import ru.petukhov.sto_dorog.repositories.PersonRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class NewsItemServiceImpl implements NewsItemService {
    private final NewsItemRepository newsItemRepository;
    private final PersonRepository personRepository;
    private final AmazonClientService amazonClientService;


    public NewsItemServiceImpl(NewsItemRepository newsItemRepository, PersonRepository personRepository, AmazonClientService amazonClientService) {
        this.newsItemRepository = newsItemRepository;
        this.personRepository = personRepository;
        this.amazonClientService = amazonClientService;
    }

    @Override
    public Iterable<NewsItem> findAll() {
        return newsItemRepository.findAll();
    }

    @Override
    public NewsItem findById(Long id) {
        return newsItemRepository.findById(id).orElseThrow(() -> new NewsItemNotFoundException(String.format("NewsItem with %s id not found", id)));
    }

    @Override
    public NewsItem createNewsItem(NewsItemDto newsItemDto, Principal principal, MultipartFile multipartFile) {
        NewsItem newsItem = new NewsItem();
        Optional<Person> person = personRepository.findByLogin(principal.getName());
        newsItem.setTitle(newsItemDto.getTitle());
        newsItem.setAnons(newsItemDto.getAnons());
        newsItem.setFullText(newsItemDto.getFullText());
        newsItem.setTime(LocalDateTime.now());
       newsItem.setUrlImage(getUrlImage(multipartFile));
        newsItem.setStr("Добавлено: ");
        if (person.isPresent())
            newsItem.setPerson(person.orElseThrow(() -> new PersonNotFoundException("Person not found")));
        newsItemRepository.save(newsItem);
        return newsItem;
    }

    @Override
    public NewsItem updateNewsItem(NewsItemDto newsItemDto, Long id, MultipartFile multipartFile) {
        NewsItem newsItem = findById(id).toBuilder().title(newsItemDto.getTitle())
                .anons(newsItemDto.getAnons()).fullText(newsItemDto.getFullText())
                .time(LocalDateTime.now()).str("Изменено: ").urlImage(getUrlImage(multipartFile)).build();
        newsItemRepository.save(newsItem);
        return newsItem;
    }

    @Override
    public void deleteNewsItem(Long id) {
        amazonClientService.deleteFileFromS3Bucket(findById(id).getUrlImage());
        newsItemRepository.delete(findById(id));
    }

    @Override
    public boolean findNewsItem(Long id) {
        return newsItemRepository.existsById(id);
    }

    @Override
    public void setViewsNewsItem(Long id) {
        NewsItem newsItem = findById(id);
        int count = newsItem.getViews();
        newsItem.setViews(++count);
        newsItemRepository.save(newsItem);
    }

    @Override
    public Page<NewsItem> getOldNews(Pageable pageable) {
        return newsItemRepository.findAllByOrderByTimeAsc(pageable);
    }

    @Override
    public Page<NewsItem> getRecentNews(Pageable pageable) {
        return newsItemRepository.findAllByOrderByTimeDesc(pageable);
    }

    @Override
    public Page<NewsItem> getNews(Pageable pageable) {
        return newsItemRepository.findAll(pageable);
    }

    @Override
    public NewsItem getLatestNews(Pageable pageable) {
        return newsItemRepository.findAllByOrderByTimeDesc(pageable).stream().findFirst().orElseThrow(() -> new NewsItemNotFoundException("NewsItem with %s id not found"));
    }

    public String getUrlImage(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return "https://sch1290.mskobr.ru/files/1%20Foto/s1200.png";
        } else{
           return amazonClientService.uploadFile(multipartFile);
        }
    }
}
