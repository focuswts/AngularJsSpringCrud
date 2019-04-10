package com.angularjs.crud.angularjsSpringCrud.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(value = "/addUsersCSV")
	@ResponseBody
	public ResponseEntity<?> addUsersCSV(@RequestParam(value = "file", required = false) MultipartFile fd) {
		System.out.println("Read CSV");

		BufferedReader br;
		List<String> result = new ArrayList<>();
		try {
			List<User> usuarios = new ArrayList<User>();
			String line;
			InputStream is = fd.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				result.add(line);
				String[] values = line.split(",");

				if (!values[0].equals("id")) {
					User user = new User();
					user.setId(Integer.valueOf(values[0]));
					user.setName(values[1]);
					user.setPassword(values[2]);
					user.setEmail(values[3]);
					usuarios.add(user);
				}

			}
			try {
				userRepository.saveAll(usuarios);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

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
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {

		userRepository.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		System.out.println("Delete");

		User user = new User();
		user.setId(id);

		userRepository.delete(user);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
