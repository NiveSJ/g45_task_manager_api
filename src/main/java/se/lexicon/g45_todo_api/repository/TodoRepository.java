package se.lexicon.g45_todo_api.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.g45_todo_api.model.entity.Todo;

import java.util.List;


public interface TodoRepository extends CrudRepository<Todo, Integer> {
        List<Todo> findByTitleIgnoreCase(String title);
        List<Todo> findAllByOrderByIdDesc();
        List<Todo> findAllByAssignee(String email );
}
