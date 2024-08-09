package com.moviesplus.catalogapp.user;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	
	private final UserService userService;
	
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String home(){
		return "Hello World";
	}


	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/usersbyresttemplate")
	public User getByUsingRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject("http://localhost:8080/user" + "/1", User.class);
		return user;
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Integer id){
		return userService.getUserById(id);
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		return newUser;
	}
	
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
	
	@PutMapping("/user/{id}")
	public User updateUserById(  @PathVariable Integer id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	
	
	
}
