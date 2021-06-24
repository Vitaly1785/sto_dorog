package ru.petukhov.sto_dorog.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.services.NewsItemService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/news")
public class NewsItemController {
    private String sortMethod = "ASC";
    private final NewsItemService newsItemService;

    public NewsItemController(NewsItemService newsItemService) {
        this.newsItemService = newsItemService;
    }

    @GetMapping
    public String findAllNews(Model model, @PageableDefault Pageable pageable) {
        Page<NewsItem> page = sortNewsItem(pageable);
        model.addAttribute("page", page);
        return "/news";
    }

    @GetMapping("/{id}")
    public String findNewsItem(@PathVariable("id") Long id, Model model) {
        if (!newsItemService.findNewsItem(id)) {
            return "/news";
        }

        model.addAttribute("newsItem", newsItemService.findById(id));
        newsItemService.setViewsNewsItem(id);
        return "/newsItem";
    }

    @GetMapping("/add")
    public String addNewsItem(Model model) {
        model.addAttribute("newsItem", new NewsItem());
        return "/addNewsItem";
    }

    @PostMapping
    public String createNewsItem(@ModelAttribute("newsItem") @Valid NewsItemDto newsItemDto,
                                 BindingResult result, Principal principal, @RequestParam(value = "file") MultipartFile file) {
        if (result.hasErrors()){
            return "/addNewsItem";
        }
        newsItemService.createNewsItem(newsItemDto, principal, file);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String editNewsItem(Model model, @PathVariable("id") Long id) {
        if (!newsItemService.findNewsItem(id)) {
            return "/news";
        }
        model.addAttribute("newsItem", newsItemService.findById(id));
        return "/editNewsItem";
    }

    @PatchMapping("/{id}")
    public String updateNewsItem(@PathVariable("id") Long id, @ModelAttribute("newsItem") @Valid NewsItemDto newsItemDto,
                                 BindingResult result, @RequestParam("file") MultipartFile multipartFile) {
        if (result.hasErrors()){
            return "/editNewsItem";
        }
        newsItemService.updateNewsItem(newsItemDto, id, multipartFile);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deleteNewsItem(@PathVariable("id") Long id) {
        newsItemService.deleteNewsItem(id);
        return "redirect:/news";
    }

    @PostMapping("/{sorted}")
    public String getSortMethod(@PathVariable("sorted") String sorted) {
        sortMethod = sorted;
        return "redirect:/news";
    }


    public Page<NewsItem> sortNewsItem(Pageable pageable) {
        Page<NewsItem> newsItems;

        switch (sortMethod) {
            case "ASC":
                newsItems = newsItemService.getOldNews(pageable);
                break;
            case "DESC":
                newsItems = newsItemService.getRecentNews(pageable);
                break;
            default:
                newsItems = newsItemService.getNews(pageable);
                break;
        }
        return newsItems;
    }
}
