package se.lexicon.g45_todo_api.service;

import se.lexicon.g45_todo_api.model.dto.TodoDto;

import java.util.List;

public interface TodoService {

    List<TodoDto> getAll();
    List<TodoDto> findByTitle(String title);


    TodoDto findById(Integer TodoId);

    TodoDto create(TodoDto todoDto);

    void update(TodoDto todoDto);

    void delete(Integer todoId);


}
