package se.lexicon.g45_todo_api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    private String title;
    private String Description;
    private LocalDate deadline;
    private String status;

    public Todo() {
    }
}
