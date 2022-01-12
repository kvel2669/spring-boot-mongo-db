package com.nosql.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nosql.exception.TodoException;
import com.nosql.message.Message;
import com.nosql.model.TodoDTO;
import com.nosql.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<TodoDTO>> getAllTodo(){
		return new ResponseEntity<List<TodoDTO>>(todoService.getAllTodo(),HttpStatus.OK); 
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<TodoDTO> saveTodo(@RequestBody TodoDTO todoDTO){
		System.out.println(todoDTO.toString());
		todoService.save(todoDTO);
		return new ResponseEntity<>(todoDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<?> replaceById(@PathVariable("id") String id,@RequestBody TodoDTO todoDTO){
		return new ResponseEntity<TodoDTO>(todoService.replaceTodo(id, todoDTO),HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")String id){
		TodoDTO todoDTO = null;
		Message message= new Message();
		System.out.println("id --> "+id);
		try{
			todoDTO=todoService.getById(id);
			return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.OK);
		}catch(TodoException todoException) {
			message.setCode("BAD_REQUEST");
			message.setMessage(todoException.getMessage());
			message.setDateTime(LocalDateTime.now());
			return new ResponseEntity<Message>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/Id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")String id){
		return new ResponseEntity<String>("Deleted successfully !",HttpStatus.OK);
		
	}
	
	@PostMapping("/saveTodo")
	public ResponseEntity<?> findByTodo(@RequestBody TodoDTO todoDTO){
		try {
			todoService.saveTodo(todoDTO);
			return new ResponseEntity<String>("Successfully Created",HttpStatus.CREATED);
		} catch ( TodoException | ConstraintViolationException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/alltodos")
	public ResponseEntity<?> getAlltodos(){
		return new ResponseEntity<List<String>>(todoService.getOnlyTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/completed")
	public ResponseEntity<?> findTodosWithCompleted(@RequestParam("flag") Boolean flag){
		return new ResponseEntity<List<TodoDTO>>(todoService.getTodosWithCompleted(flag),HttpStatus.OK);
	}
}




