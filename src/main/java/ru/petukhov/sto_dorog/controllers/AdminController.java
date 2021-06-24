package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.dto.PersonUpdateDto;
import ru.petukhov.sto_dorog.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin-panel")
public class AdminController {

    private final PersonService personService;

    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String showAllPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "/personsAdmin";
    }

    @GetMapping("/{login}")
    public String showPersonByLogin(Model model, @PathVariable String login){
        model.addAttribute("person", personService.findByLogin(login));
        return "/showPersonForAdmin";
    }

    @GetMapping("/{login}/edit")
    public String editPerson(Model model, @PathVariable("login") String login){
        model.addAttribute("editPerson", personService.findByLogin(login));
        return "/editPersonByAdmin";
    }

    @PatchMapping("/{login}")
    public String updatePerson(@PathVariable("login") String login, @ModelAttribute("editPerson") @Valid PersonUpdateDto personDto,
                               BindingResult result){
        if (result.hasErrors()){
            return "/editPersonByAdmin";
        }
        personService.updatePerson(personDto, login);
        return "redirect:/admin-panel";
    }

    @DeleteMapping("/{login}")
    public String deletePerson(@PathVariable String login){
        personService.deletePerson(login);
        return "redirect:/admin-panel";
    }
}
