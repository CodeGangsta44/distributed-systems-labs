package edu.kpi.todoapp.model.service;

import edu.kpi.todoapp.model.entity.ToDoList;
import edu.kpi.todoapp.model.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;

    public TodoListService(final TodoListRepository todoListRepository) {

        this.todoListRepository = todoListRepository;
    }

    public void createList(final String name) {

        final var toDoList = new ToDoList();
        toDoList.setId(UUID.randomUUID());
        toDoList.setName(name);
        toDoList.setCreated(LocalDateTime.now());

        todoListRepository.save(toDoList);
    }

    public Optional<ToDoList> getById(final String uuid) {

        return todoListRepository.findById(UUID.fromString(uuid));
    }

    public List<ToDoList> findAll() {

        return todoListRepository.findAllByOrderByCreatedAsc();
    }

    public void deleteById(final String uuid) {

        todoListRepository.deleteById(UUID.fromString(uuid));
    }
}
