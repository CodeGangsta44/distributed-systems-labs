package edu.kpi.todoapp.controller;

import edu.kpi.todoapp.model.entity.ToDoList;
import edu.kpi.todoapp.model.entity.ToDoListEntry;
import edu.kpi.todoapp.model.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

import static edu.kpi.todoapp.constant.AppConstants.Attributes.ID;
import static edu.kpi.todoapp.constant.AppConstants.Attributes.LISTS;
import static edu.kpi.todoapp.constant.AppConstants.Attributes.NAME;
import static edu.kpi.todoapp.constant.AppConstants.Pages.REDIRECT;
import static edu.kpi.todoapp.constant.AppConstants.Pages.ROOT;

@Controller
@RequestMapping("/")
public class TodoController {

    private final TodoListService todoListService;

    public TodoController(final TodoListService todoListService) {

        this.todoListService = todoListService;
    }

    @GetMapping
    public String getList(final Model model) {

        final List<ToDoList> lists = todoListService.findAll();
        lists.forEach(list -> list.getEntries().sort(Comparator.comparing(ToDoListEntry::getCreated)));

        model.addAttribute(LISTS, lists);

        return ROOT;
    }

    @PostMapping
    public String createList(@RequestBody MultiValueMap<String, String> map) {

        todoListService.createList(map.getFirst(NAME));

        return REDIRECT;
    }

    @PostMapping("/delete")
    public String deleteList(@RequestBody MultiValueMap<String, String> map) {

        todoListService.deleteById(map.getFirst(ID));

        return REDIRECT;
    }
}
