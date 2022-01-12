package com.nosql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nosql.model.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

	@Query("{'todo': ?0}")
	List<Optional<TodoDTO>> findByTodo(String todo);
	
	@Query("{},{'todo':1,'_id':0}")
	List<TodoDTO> getOnlyTodos();
	
	@Query("{'completed': ?0}")
	List<TodoDTO> getTodosWithCompleted(Boolean flag);
}
