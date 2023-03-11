package se.lexicon.g45_todo_api.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.g45_todo_api.model.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
        Optional<Todo> findByTitle(String title);
        List<Todo> findAllByOrderByIdDesc();
        List<Todo> findAllByAssignee();
}
