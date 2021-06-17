package ru.petukhov.sto_dorog.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.entities.UserImage;
import ru.petukhov.sto_dorog.exceptions.ImageNotFoundException;
import ru.petukhov.sto_dorog.repositories.ImageRepository;
import ru.petukhov.sto_dorog.repositories.PersonRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepo;
    private final AmazonClientService amazonClientService;
    private final PersonRepository personRepository;

    public ImageService(ImageRepository imageRepo, AmazonClientService amazonClientService, PersonRepository personRepository) {
        this.imageRepo = imageRepo;
        this.amazonClientService = amazonClientService;
        this.personRepository = personRepository;
    }

    public Iterable<UserImage> showImages(){
        return imageRepo.findAll();
    }

    public UserImage showImageById(Long id){
        return imageRepo.findById(id).orElseThrow(()-> new ImageNotFoundException(String.format("Image with id %s not found", id)));
    }

    public UserImage createImage(String title, MultipartFile multipartFile, Principal principal){
        UserImage createdImage = new UserImage();
        Optional<Person> person = personRepository.findByLogin(principal.getName());
        createdImage.setTitle(title);
        createdImage.setTime(LocalDateTime.now());
        person.ifPresent(createdImage::setPerson);
        createdImage.setUrl(amazonClientService.uploadFile(multipartFile));
        imageRepo.save(createdImage);
        return createdImage;
    }
    public void deleteImage(Long id){
        Optional<UserImage> imageOptional = imageRepo.findById(id);
        if (imageOptional.isPresent()){
            amazonClientService.deleteFileFromS3Bucket(imageOptional.get().getUrl());
            imageRepo.delete(imageOptional.get());
        }
    }
}
