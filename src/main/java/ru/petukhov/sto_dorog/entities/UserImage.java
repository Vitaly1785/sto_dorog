package ru.petukhov.sto_dorog.entities;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "images")
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @NotNull(message = "Добавьте фото")
    private String url;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private LocalDateTime time;
}
