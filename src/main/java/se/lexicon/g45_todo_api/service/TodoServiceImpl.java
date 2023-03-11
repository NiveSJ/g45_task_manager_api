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
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<TodoDto> getAll() {
        List<Todo> todos = todoRepository.findAllByOrderByIdDesc();
        return modelMapper.map(todos, new TypeToken<List<TodoDto>>() {
        }.getType());
    }

    public List<TodoDto> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("title cannot be null");
        List<Todo> todos = todoRepository.findByTitleIgnoreCase(title);
        return modelMapper.map(todos, new TypeToken<List<TodoDto>>() {
        }.getType());
    }

    public List<TodoDto> findByUser(String email) {
        if (email == null) throw new IllegalArgumentException("From Todo service layer Email cannot be null");
        List<Todo> todos = todoRepository.findAllByAssignee(email);
        return modelMapper.map(todos, new TypeToken<List<TodoDto>>() {
        }.getType());

    }

    @Override
    public TodoDto findById(Integer TodoId) {
        if (TodoId == null) throw new IllegalArgumentException("From Todo service layer Todo id was null");
        Optional<Todo> optionalRole = todoRepository.findById(TodoId);
        if (optionalRole.isPresent()) {
            Todo entity = optionalRole.get();
            return modelMapper.map(entity, TodoDto.class);
        }
        return null;
    }


    @Override
    public TodoDto create(TodoDto todoDto) {
        if (todoDto == null) throw new IllegalArgumentException(">> From Todo service layer :todo data was null");
        if (todoDto.getId() != 0) throw new IllegalArgumentException(">> From Todo service layer :todo id should be null or zero");
        Todo createdEntity = todoRepository.save(modelMapper.map(todoDto, Todo.class));
        return modelMapper.map(createdEntity, TodoDto.class);
    }

    @Override
    public void update(TodoDto todoDto) {
        if (todoDto == null) throw new IllegalArgumentException(">> From Todo service layer :todo data was null");
        if (todoDto.getId() == 0)
            throw new IllegalArgumentException(">> From Todo service layer :role id should not be zero");

        if (!todoRepository.findById(todoDto.getId()).isPresent())
            throw new DataNotFoundException(">> From Todo service layer :data not found error");
        todoRepository.save(modelMapper.map(todoDto, Todo.class));

    }

    @Override
    public void delete(Integer roleId) {

        TodoDto roleDto = findById(roleId);
        if (roleDto == null) throw new DataNotFoundException(">> From Todo service layer :id was not valid");
        todoRepository.deleteById(roleId);
    }


}
