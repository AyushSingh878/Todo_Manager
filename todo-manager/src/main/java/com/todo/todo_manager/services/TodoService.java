package com.todo.todo_manager.services;

import com.todo.todo_manager.models.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {
    List<Todo> todos=new ArrayList<>();
    public Todo createTodo(Todo todo){
        todos.add(todo);
        return todo;
    }

    public List<Todo> getAllTodos(){
        return todos;
    }

    public Todo getTodo(int todoId){
        Todo todo = todos.stream().filter(t -> todoId == t.getId()).findAny().get();
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo){
        List<Todo> newUpdateList = todos.stream().map(t -> {
            if(t.getId() == todoId){
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            }else{
                return t;
            }
        }).collect(Collectors.toList());

        todos = newUpdateList;
        todo.setId(todoId);
        return todo;
    }

    public void deleteTodo(int todoId){
        List<Todo> newList = todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
        todos=newList;
    }
}
