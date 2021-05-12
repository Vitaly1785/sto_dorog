package ru.petukhov.sto_dorog.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    private UUID id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
