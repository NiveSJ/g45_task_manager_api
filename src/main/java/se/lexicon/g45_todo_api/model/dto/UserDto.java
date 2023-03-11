package se.lexicon.g45_todo_api.model.dto;

import lombok.*;
import se.lexicon.g45_todo_api.model.entity.Todo;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private int id;
    private String firstName;

    private String lastname;

    private String email;
    private List<Todo> todoList = new ArrayList<>();
}
