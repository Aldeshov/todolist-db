package com.example.tododb.controllers;

import com.example.tododb.models.Todo;
import com.example.tododb.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/todos", produces = { "application/json" })
    public ResponseEntity<List<Todo>> todos(){
        return new ResponseEntity<>(todoRepository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/todos", produces = { "application/json" })
    public ResponseEntity<?> createToDo(@RequestBody Todo todo){
        todoRepository.save(todo);
        return new ResponseEntity<>("Created", HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value = "todos/{id}", produces = { "application/json" })
    public ResponseEntity<?> updateToDo(@PathVariable Long id, @RequestBody Todo data) {
        Todo todo = todoRepository.findOne(id);
        if (todo != null && data != null) {
            todo.setDate(data.getDate());
            todo.setTitle(data.getTitle());
            todo.setPriority(data.getPriority());
            todo.setDescription(data.getDescription());
            todoRepository.save(todo);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value = "todos/{id}/", produces = { "application/json" })
    public ResponseEntity<?> updateToDoDone(@PathVariable Long id, @RequestParam boolean done) {
        Todo todo = todoRepository.findOne(id);
        if (todo != null) {
            todo.setDone(done);
            todoRepository.save(todo);
            return new ResponseEntity<>("Done", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("todos/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable Long id){
        todoRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<Todo> todo(@PathVariable Long id){
        return new ResponseEntity<>(todoRepository.findOne(id), HttpStatus.OK);
    }
}
