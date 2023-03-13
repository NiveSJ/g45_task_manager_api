package se.lexicon.g45_todo_api.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g45_todo_api.exception.DataNotFoundException;
import se.lexicon.g45_todo_api.model.dto.TodoDto;
import se.lexicon.g45_todo_api.model.dto.UserDto;
import se.lexicon.g45_todo_api.model.entity.Todo;
import se.lexicon.g45_todo_api.model.entity.User;
import se.lexicon.g45_todo_api.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAllByOrderByIdDesc();
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    public UserDto  getAllByEmail(String email) {
      Optional<User> users = userRepository.findByEmailIgnoreCase(email);
        return modelMapper.map(users, UserDto.class);
    }

    @Override
    public UserDto findById(Integer userId) {
        if (userId == null) throw new IllegalArgumentException(">> From service layer User id is null");
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            User entity = userById.get();
            return modelMapper.map(entity, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException(">> From Todo service layer :todo data was null");
        if (userDto.getId() != 0)
            throw new IllegalArgumentException(">> From Todo service layer :todo id should be null or zero");
        User createdEntity = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(createdEntity, UserDto.class);
    }

    @Override
    public void update(UserDto userDto) {
        if (userDto == null) throw new IllegalArgumentException(">>from service layer :user data is null");
        if (userDto.getId() == 0)
            throw new IllegalArgumentException("From Service layer: User to be updated cannot have null id");
        if (!userRepository.findById(userDto.getId()).isPresent())
            throw new DataNotFoundException(">> From User service layer :data not found error");
        userRepository.save(modelMapper.map(userDto, User.class));


    }

    @Override
    public void delete(Integer userId) {
        if(userId == null) throw new IllegalArgumentException(">> From Todo service layer :todo Id cannot be null");

        UserDto userDto = findById(userId);
        if (userDto == null) throw new DataNotFoundException(">> From Todo service layer :id was not valid");
        userRepository.deleteById(userId);
    }
}
