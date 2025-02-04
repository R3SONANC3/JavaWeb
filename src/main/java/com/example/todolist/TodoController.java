package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todoindex")
    public String home(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newTodo", new Todo());
        return "todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        Todo todo = todoService.todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(!todo.isCompleted());
        todoService.updateTodo(id, todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
}