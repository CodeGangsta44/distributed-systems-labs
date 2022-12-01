package edu.kpi.todoapp.model.repository;

import edu.kpi.todoapp.model.entity.ToDoListEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoListEntryRepository extends JpaRepository<ToDoListEntry, UUID> {
}
