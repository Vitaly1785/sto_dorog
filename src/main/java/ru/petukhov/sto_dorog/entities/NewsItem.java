package ru.petukhov.sto_dorog.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
@Data
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String anons;
    @Column(name = "full_text")
    private String fullText;

    private int views;
    private LocalDateTime time;
    private String str;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
