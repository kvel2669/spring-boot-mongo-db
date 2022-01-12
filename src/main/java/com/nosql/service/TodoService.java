package com.nosql.service;

import java.util.List;

import com.nosql.exception.TodoException;
import com.nosql.model.TodoDTO;

public interface TodoService {

	public List<TodoDTO> getAllTodo();
	public void save(TodoDTO todoDTO);
	public TodoDTO getById(String id) throws TodoException;
	public TodoDTO replaceTodo(String Id,TodoDTO todoDTO);
	public void deleteById(String id);
	void saveTodo(TodoDTO todoDTO)throws TodoException;
	List<String> getOnlyTodos();
	List<TodoDTO> getTodosWithCompleted(Boolean flag);
}
