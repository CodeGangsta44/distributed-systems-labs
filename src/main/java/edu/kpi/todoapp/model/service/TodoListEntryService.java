package edu.kpi.todoapp.model.service;

import edu.kpi.todoapp.model.entity.ToDoList;
import edu.kpi.todoapp.model.entity.ToDoListEntry;
import edu.kpi.todoapp.model.repository.TodoListEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TodoListEntryService {

    private final TodoListEntryRepository todoListEntryRepository;
    private final TodoListService todoListService;

    public TodoListEntryService(final TodoListEntryRepository todoListEntryRepository, final TodoListService todoListService) {

        this.todoListEntryRepository = todoListEntryRepository;
        this.todoListService = todoListService;
    }

    public void addEntryToList(final String listId, final String entryValue) {

        todoListService.getById(listId)
                .ifPresent(list -> addEntryToList(list, entryValue));
    }

    public void updateEntry(final String entryId, final boolean status) {

        todoListEntryRepository.findById(UUID.fromString(entryId))
                .ifPresent(entry -> {
                    entry.setStatus(status);
                    todoListEntryRepository.save(entry);
                });
    }

    public void removeEntryFromList(final String id) {

        todoListEntryRepository.deleteById(UUID.fromString(id));
    }

    private void addEntryToList(final ToDoList list, final String entryValue) {

        final var entry = new ToDoListEntry();
        entry.setId(UUID.randomUUID());
        entry.setValue(entryValue);
        entry.setParent(list);
        entry.setCreated(LocalDateTime.now());

        todoListEntryRepository.save(entry);
    }
}
