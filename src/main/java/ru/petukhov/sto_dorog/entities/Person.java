package ru.petukhov.sto_dorog.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Data
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
}
