package se.lexicon.g45_todo_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g45_todo_api.model.dto.TodoDto;
import se.lexicon.g45_todo_api.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    TodoService roleService;


    // http://localhost:8080/api/v1/role/
    @GetMapping("/")
    public ResponseEntity<List<TodoDto>> getAll() {
        //return ResponseEntity.ok(roleService.getAll()); // 200
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        roleService.delete(id);
        //return ResponseEntity.noContent().build(); // 204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<TodoDto> create(@RequestBody TodoDto dto){
        TodoDto createdROleDto = roleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdROleDto); // 201
    }


    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody TodoDto dto){
        roleService.update(dto);
        return ResponseEntity.noContent().build();
    }


}

