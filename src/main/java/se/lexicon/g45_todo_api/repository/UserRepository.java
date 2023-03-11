package se.lexicon.g45_todo_api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.g45_todo_api.model.entity.User;

import java.util.Optional;


public interface UserRepository extends  CrudRepository<User, String> {

    Optional<User> findByEmail(String email);

}
