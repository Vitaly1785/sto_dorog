package ru.petukhov.sto_dorog.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.petukhov.sto_dorog.entities.AmazonImage;

public interface AmazonImageRepository extends CrudRepository<AmazonImage, String> {
}