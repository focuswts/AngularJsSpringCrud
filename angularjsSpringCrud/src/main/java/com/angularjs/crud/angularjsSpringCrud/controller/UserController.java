package com.angularjs.crud.angularjsSpringCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angularjs.crud.angularjsSpringCrud.entity.User;
import com.angularjs.crud.angularjsSpringCrud.repository.UserRepository;

@Controller
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/add")
	public ResponseEntity<String> addNewUser(@RequestBody User user) {

		userRepository.save(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUsers() {
		System.out.println("Listagem Usuarios");
		List<User> users = (List<User>) userRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody User user) {
		
		userRepository.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		System.out.println("Delete");
		
		User user = new User();
		user.setId(id);
		
		userRepository.delete(user);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
