package edu.kpi.todoapp.controller;

import edu.kpi.todoapp.model.entity.ToDoList;
import edu.kpi.todoapp.model.entity.ToDoListEntry;
import edu.kpi.todoapp.model.service.TodoListEntryService;
import edu.kpi.todoapp.model.service.TodoListService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import static edu.kpi.todoapp.constant.AppConstants.Attributes.TODO;
import static edu.kpi.todoapp.constant.AppConstants.Pages.DETAILS;
import static edu.kpi.todoapp.constant.AppConstants.Pages.REDIRECT;

@Controller
@RequestMapping("/todo")
public class TodoDetailsController {

    private final Map<String, BiConsumer<String, MultiValueMap<String, String>>> updateHandlers = Map.ofEntries(
            Map.entry("add", this::addEntry),
            Map.entry("delete", this::deleteEntry),
            Map.entry("update", this::updateEntries));

    private final TodoListService todoListService;
    private final TodoListEntryService todoListEntryService;

    public TodoDetailsController(final TodoListService todoListService, final TodoListEntryService todoListEntryService) {

        this.todoListService = todoListService;
        this.todoListEntryService = todoListEntryService;

    }

    @GetMapping("/{uuid}")
    public String getDetails(final Model model, @PathVariable("uuid") final String uuid) {

        final ToDoList todo = todoListService.getById(uuid).orElseThrow();

        todo.getEntries().sort(Comparator.comparing(ToDoListEntry::getCreated));

        model.addAttribute(TODO, todo);

        return DETAILS;
    }

    @PostMapping(value = "/{uuid}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateTodo(@PathVariable("uuid") final String uuid, @RequestBody final MultiValueMap<String, String> form) {

        form.forEach((key, value) -> Optional.ofNullable(updateHandlers.get(key))
                .ifPresent(handler -> handler.accept(uuid, form)));

        if (form.containsKey("add") || form.containsKey("delete")) {

            return REDIRECT + "todo/" + uuid;
        }

        return REDIRECT;
    }

    private void addEntry(final String listId, final MultiValueMap<String, String> form) {

        todoListEntryService.addEntryToList(listId, form.get("newValue").get(0));
    }

    private void updateEntries(final String listId, final MultiValueMap<String, String> form) {

        form.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("uuidForUpdate"))
                .forEach(entry -> todoListEntryService.updateEntry(entry.getKey().split("uuidForUpdate")[1], entry.getValue().contains("on")));
    }

    private void deleteEntry(final String listId, final MultiValueMap<String, String> form) {

        todoListEntryService.removeEntryFromList(form.get("delete").get(0));
    }

}
