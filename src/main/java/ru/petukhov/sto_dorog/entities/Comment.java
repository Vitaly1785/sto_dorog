package ru.petukhov.sto_dorog.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "news_item_id")
    private NewsItem newsItem;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, updatable = false)
    private Person person;
}
