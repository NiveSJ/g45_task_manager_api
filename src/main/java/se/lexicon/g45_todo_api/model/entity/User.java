package se.lexicon.g45_todo_api.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.g45_todo_api.exception.DataDuplicateException;
import se.lexicon.g45_todo_api.exception.DataNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor

@Data

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(updatable = false)
    private int id;
    private String firstName;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true,updatable = false)
    private String email;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH} ,mappedBy = "Assignee")

    private List<Todo> todoList = new ArrayList<>();

    public User() {
    }

    public void addTodoList(Todo todo) {

        if(todo== null) throw new IllegalArgumentException(">> From User Entity: Todo cannot be empty");
        todoList.add(todo);
        todo.setAssignee(this);  // Two way mapping
    }

    public void removeTodoList(Todo role) {

        todoList.remove(role);
    }


}
