package com.todo.todo_manager.controllers;

import com.todo.todo_manager.models.Todo;
import com.todo.todo_manager.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;
    Random random=new Random();
    @PostMapping("/")
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        //create todo
        int id=random.nextInt(9999999);
        todo.setId(id);
        logger.info("Create Todo");
        //call service to create todo

        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId){
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Successfully deleted");
    }
}
