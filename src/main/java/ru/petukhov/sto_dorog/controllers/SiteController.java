package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SiteController {

    @GetMapping
    public String getGreeting(){
        return "/greeting";
    }

    @GetMapping("/tours")
    public String getTours(){
        return "/tours";
    }

    @GetMapping("/contacts")
    public String getContacts(){
        return "/contacts";
    }
}
