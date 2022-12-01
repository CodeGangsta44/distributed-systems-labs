package edu.kpi.todoapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "listentries")
@Data
public class ToDoListEntry {

    @Id
    private UUID id;

    private String value;
    private boolean status;

    @ManyToOne
    private ToDoList parent;

    private LocalDateTime created;
}
