package se.lexicon.g45_todo_api.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.g45_todo_api.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends  CrudRepository<User, Integer> {

Optional<User> findByEmailIgnoreCase(String email);
    List<User> findAllByOrderByIdDesc();

}
