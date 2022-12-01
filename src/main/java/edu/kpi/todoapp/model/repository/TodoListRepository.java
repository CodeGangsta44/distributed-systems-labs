package edu.kpi.todoapp.model.repository;

import edu.kpi.todoapp.model.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoListRepository extends JpaRepository<ToDoList, UUID> {

    List<ToDoList> findAllByOrderByCreatedAsc();
}
