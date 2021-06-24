package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.dto.UpdateByPersonDto;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/persons")
public class PersonsController {

    private final PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String showPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "/persons";
    }

    @GetMapping("/{login}")
    public String showPersonByLogin(Model model, @PathVariable String login){
        model.addAttribute("person", personService.findByLogin(login));
        return "/showPerson";
    }

    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("newPerson", new Person());
        return "/addPerson";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("newPerson") @Valid PersonDto personDto, BindingResult result,
                               @RequestParam("file") MultipartFile multipartFile){
        if (result.hasErrors()){
            return "/addPerson";
        }
        personService.createPerson(personDto, multipartFile);
        return "redirect:/persons";
    }

    @GetMapping("/{login}/edit")
    public String editPerson(Model model, @PathVariable("login") String login){
        model.addAttribute("editPerson", personService.findByLogin(login));
        return "/editPerson";
    }

    @PatchMapping("/{login}")
    public String updatePerson(@PathVariable("login") String login,
                               @ModelAttribute("editPerson") @Valid UpdateByPersonDto personDto,
                               BindingResult result, @RequestParam("file") MultipartFile multipartFile){
        if (result.hasErrors()){
            return "/editPerson";
        }
        personService.updateByPerson(personDto, login, multipartFile);
        return "redirect:/persons";
    }


    @DeleteMapping("/{login}")
    public String deletePerson(@PathVariable String login){
        personService.deletePerson(login);
        return "redirect:/news";
    }

    @GetMapping("/login")
    public String showMyLoginPage(){
        return "/login";
    }

    @RequestMapping("/logout")
    public String showLogout(){
        return "logout";
    }
}
