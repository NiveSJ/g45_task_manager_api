package se.lexicon.g45_todo_api.service;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g45_todo_api.exception.DataDuplicateException;
import se.lexicon.g45_todo_api.exception.DataNotFoundException;
import se.lexicon.g45_todo_api.model.dto.TodoDto;
import se.lexicon.g45_todo_api.model.entity.Todo;
import se.lexicon.g45_todo_api.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

// import the model mapper class
import org.modelmapper.ModelMapper;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    TodoRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<TodoDto> getAll() {
        List<Todo> roleList = roleRepository.findAllByOrderByIdDesc();
        /*return roleList.stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());*/

        return modelMapper.map(roleList, new TypeToken<List<TodoDto>>() {
        }.getType());
    }

    @Override
    public TodoDto findById(Integer roleId) {
        if (roleId == null) throw new IllegalArgumentException("role id was null");
        Optional<Todo> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Todo entity = optionalRole.get();
            return modelMapper.map(entity, TodoDto.class);
        }
        return null;
    }

    @Override
    public TodoDto create(TodoDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("role data was null");
        if (roleDto.getId() != 0) throw new IllegalArgumentException("role id should be null or zero");

        Todo createdEntity = roleRepository.save(modelMapper.map(roleDto, Todo.class));
        return modelMapper.map(createdEntity, TodoDto.class);
    }

    @Override
    public void update(TodoDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("role data was null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("role id should not be zero");

        if (!roleRepository.findById(roleDto.getId()).isPresent())
            throw new DataNotFoundException("data not found error");

        if (roleRepository.findByName(roleDto.getName()).isPresent())
            throw new DataDuplicateException("duplicate error");
        roleRepository.save(modelMapper.map(roleDto, Todo.class));

    }

    @Override
    public void delete(Integer roleId) {

        TodoDto roleDto = findById(roleId);
        if (roleDto == null) throw new DataNotFoundException("id was not valid");
        roleRepository.deleteById(roleId);
    }


}
