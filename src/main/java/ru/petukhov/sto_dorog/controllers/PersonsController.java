package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.petukhov.sto_dorog.dto.PersonDto;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.services.PersonService;



@Controller
@RequestMapping("/persons")
public class PersonsController {

    private final PersonService personService;

    public PersonsController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/admin-panel")
    public String showAllPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "/personsAdmin";
    }
    @GetMapping
    public String showPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "/persons";
    }

//    @GetMapping("/{id}")
//    public String showPerson(@PathVariable Long id, Model model){
//        model.addAttribute("person", personService.findById(id));
//        return "/showPerson";
//    }
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
    public String createPerson(@ModelAttribute("newPerson")PersonDto personDto){
        personService.createPerson(personDto);
        return "redirect:/news";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable Long id){
        model.addAttribute("editPerson", personService.findById(id));
        return "/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("editPerson") PersonDto personDto, @PathVariable Long id){
        personService.updatePerson(personDto, id);
        return "redirect:/news";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
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
