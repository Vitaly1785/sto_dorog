package ru.petukhov.sto_dorog.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
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

    @OneToMany(mappedBy = "newsItem", orphanRemoval = true)
    private List<Comment> comments;

    @Column(name = "url_image")
    private String urlImage;

}
