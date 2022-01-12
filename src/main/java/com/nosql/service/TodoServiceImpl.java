package com.nosql.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nosql.exception.TodoException;
import com.nosql.model.TodoDTO;
import com.nosql.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<TodoDTO> getAllTodo() {
		return todoRepository.findAll();
	}

	@Override
	public void save(TodoDTO todoDTO) {
		todoDTO.setCreatedAt(LocalDateTime.now());
		todoRepository.save(todoDTO);
	}

	@Override
	public TodoDTO getById(String id) throws TodoException {
	if(todoRepository.findById(id).isPresent())
		return todoRepository.findById(id).get();
	throw new TodoException("Invalid ID");
	}

	@Override
	public TodoDTO replaceTodo(String Id, TodoDTO todoDTO) {
		return todoRepository.findById(Id)
		.map(todo ->{
			todo.setDescription(todoDTO.getDescription());
			todo.setTodo(todoDTO.getTodo());
			todo.setCompleted(todoDTO.getCompleted());
			todo.setUpdatedAt(LocalDateTime.now());
			return todoRepository.save(todo);
		}).orElseGet(()->{
			   	todoDTO.setCreatedAt(LocalDateTime.now());
		return todoRepository.save(todoDTO);
		});
	}

	@Override
	public void deleteById(String id) {
		todoRepository.deleteById(id);
		
	}

	@Override
	public void saveTodo(TodoDTO todoDTO) throws TodoException{
		if(todoRepository.findByTodo(todoDTO.getTodo()).size() >= 1) 
		  throw new TodoException(" Todo is Present");
		todoDTO.setCreatedAt(LocalDateTime.now());
		todoDTO.setUpdatedAt(null);
		todoRepository.save(todoDTO);
	}

	@Override
	public List<String> getOnlyTodos() {
		return todoRepository.getOnlyTodos().stream().map(todo-> todo.getTodo()).toList();
	}

	@Override
	public List<TodoDTO> getTodosWithCompleted(Boolean flag) {
		return todoRepository.getTodosWithCompleted(flag);
	}

	
	
}
