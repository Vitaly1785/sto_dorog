package ru.petukhov.sto_dorog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.services.AmazonClientService;
import ru.petukhov.sto_dorog.services.ImageService;

import java.security.Principal;


@Controller
@RequestMapping("/photo")
public class ImageController {

    private final ImageService imageService;
    private final AmazonClientService service;

    public ImageController(ImageService imageService, AmazonClientService service) {
        this.imageService = imageService;
        this.service = service;
    }

    @GetMapping
    public String showAllImages(Model model){
        model.addAttribute("images", imageService.showImages());
        return "/showImages";
    }
    @GetMapping("/{id}")
    public String showImageById(Model model, @PathVariable Long id){
        model.addAttribute("image", imageService.showImageById(id));
        return "/image";
    }

    @GetMapping("/add")
    public String addImage(Model model, MultipartFile file){
        return "/addImage";
    }


    @PostMapping("/add")
    public String createImage(@RequestParam(value = "title") String title,
                              @RequestParam(value = "file") MultipartFile file,
                             Principal principal){
        if (file.isEmpty()){
            return "/addImage";
        }
        imageService.createImage(title, file, principal);
        return "redirect:/photo";
    }

    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
        return "redirect:/photo";
    }
}
