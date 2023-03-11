package se.lexicon.g45_todo_api.service;
import se.lexicon.g45_todo_api.model.dto.UserDto;

import java.util.List;

interface   UserService {
    List<UserDto> getAll();


    UserDto findById(Integer userId);

    UserDto create(UserDto userDto);

    void update(UserDto userDto);

    void delete(Integer userId);
}
