package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.petukhov.sto_dorog.dto.NewsItemDto;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.services.NewsItemService;

@Controller
@RequestMapping("/news")
public class NewsItemController {
    private final NewsItemService newsItemService;

    public NewsItemController(NewsItemService newsItemService) {
        this.newsItemService = newsItemService;
    }

    @GetMapping
    public String findAllNews(Model model){
        model.addAttribute("news", newsItemService.findAll());
        return "/news";
    }

    @GetMapping("/{id}")
    public String findNewsItem(@PathVariable Long id, Model model){
        if (!newsItemService.findNewsItem(id)){
            return "/news";
        }
        model.addAttribute("newsItem", newsItemService.findById(id));
        return "/newsItem";
    }

    @GetMapping("/add")
    public String addNewsItem(Model model){
        model.addAttribute("newsItem", new NewsItem());
        return "/addNewsItem";
    }

    @PostMapping
    public String createNewsItem(@ModelAttribute("newsItem") NewsItemDto newsItemDto){
        newsItemService.createNewsItem(newsItemDto);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String editNewsItem(Model model, @PathVariable Long id){
        if (!newsItemService.findNewsItem(id)){
            return "/news";
        }
        model.addAttribute("newsItem", newsItemService.findById(id));
        return "/editNewsItem";
    }

    @PatchMapping("/{id}")
    public String updateNewsItem(@ModelAttribute("newsItem") NewsItemDto newsItemDto, @PathVariable Long id){
        newsItemService.updateNewsItem(newsItemDto, id);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deleteNewsItem(@PathVariable Long id){
        newsItemService.deleteNewsItem(id);
        return "redirect:/news";
    }
}
