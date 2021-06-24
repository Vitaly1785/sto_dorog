package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.UserImage;

public interface ImageRepository extends CrudRepository<UserImage, Long> {
    UserImage findByUrl(String url);
    Page<UserImage> findAllByOrderByTimeDesc(Pageable pageable);
}
