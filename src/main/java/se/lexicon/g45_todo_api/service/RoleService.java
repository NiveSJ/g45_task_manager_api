package se.lexicon.g45_todo_api.service;

import se.lexicon.g45_todo_api.model.dto.TodoDto;

import java.util.List;

public interface RoleService {

    List<TodoDto> getAll();

    TodoDto findById(Integer roleId);

    TodoDto create(TodoDto roleDto);

    void update(TodoDto roleDto);

    void delete(Integer roleId);


}
