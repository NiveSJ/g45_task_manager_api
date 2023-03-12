package se.lexicon.g45_todo_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g45_todo_api.model.dto.TodoDto;
import se.lexicon.g45_todo_api.service.TodoService;

import java.util.List;
//@CrossOrigin(origins = "", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    TodoService todoService;


    // http://localhost:8080/api/v1/todo/
    @GetMapping("/")
    public ResponseEntity<List<TodoDto>> getAll() {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<List<TodoDto>> getAllByUser(@PathVariable("email") String email) {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findByUser(email));
    }
    @GetMapping("/user/{title}")
    public ResponseEntity<List<TodoDto>> getAllByTitle(@PathVariable("title") String title) {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(todoService.findByUser(title));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(todoService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        todoService.delete(id);
        //return ResponseEntity.noContent().build(); // 204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/")
    public ResponseEntity<TodoDto> create(@RequestBody TodoDto dto){
        TodoDto createdTodoDto = todoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoDto); // 201
    }
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody TodoDto dto){
        todoService.update(dto);
        return ResponseEntity.noContent().build();
    }
}

