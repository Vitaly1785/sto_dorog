package ru.petukhov.sto_dorog.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class AmazonImage {

    @Id
    private String amazonUserImageId;

    @NotNull
    private String imageUrl;
}
