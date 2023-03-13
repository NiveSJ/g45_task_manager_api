package se.lexicon.g45_todo_api.model.dto;

import lombok.*;
import se.lexicon.g45_todo_api.model.entity.User;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TodoDto {
    private int id;
    private String title;
    private String Description;
    private String status;
    private LocalDate deadline;

}
