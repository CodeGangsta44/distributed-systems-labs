package edu.kpi.todoapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "todolists")
@Data
public class ToDoList {

    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<ToDoListEntry> entries;

    private LocalDateTime created;
}
