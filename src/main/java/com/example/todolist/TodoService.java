package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        todo.setTask(todoDetails.getTask());
        todo.setCompleted(todoDetails.isCompleted());
        
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        
        todoRepository.delete(todo);
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }

    public List<Todo> getActiveTodos() {
        return todoRepository.findByCompleted(false);
    }
}