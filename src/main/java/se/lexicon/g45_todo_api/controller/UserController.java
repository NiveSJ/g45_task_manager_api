package se.lexicon.g45_todo_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g45_todo_api.model.dto.UserDto;
import se.lexicon.g45_todo_api.service.UserService;

import java.util.List;
@RestController
//@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getAllByEmail(@PathVariable("email") String email) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllByEmail(email));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findById(Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto CreatedDto = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedDto);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }


}
