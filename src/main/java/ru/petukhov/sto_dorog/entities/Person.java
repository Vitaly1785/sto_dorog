package ru.petukhov.sto_dorog.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "persons")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String login;
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "person", orphanRemoval = true)
    private List<NewsItem> newsItems;

    @Column(name = "url_photo")
    private String urlPhoto;
}
