package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.UserImage;

public interface ImageRepository extends CrudRepository<UserImage, Long> {
    UserImage findByUrl(String url);
}
