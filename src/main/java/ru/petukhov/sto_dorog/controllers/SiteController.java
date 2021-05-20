package ru.petukhov.sto_dorog.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.petukhov.sto_dorog.services.NewsItemService;

@Controller
@RequestMapping("/")
public class SiteController {

    private final NewsItemService newsItemService;

    public SiteController(NewsItemService newsItemService) {
        this.newsItemService = newsItemService;
    }

    @GetMapping
    public String getGreeting(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("latestNewsItem", newsItemService.getLatestNews(pageable));
        return "/greeting";
    }

    @GetMapping("/tours")
    public String getTours() {
        return "/tours";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "/contacts";
    }
}
