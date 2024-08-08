package com.moviesplus.catalogapp.user;

import java.util.List;
import org.springframework.stereotype.Service;

import com.moviesplus.catalogapp.user.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public boolean checkUserExistsById(Integer id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found!");
		}
		return true;
	}
	
	public void deleteUser(Integer id) {
		if(checkUserExistsById(id)) {
			userRepository.deleteById(id);
		}
	}
	
	public User updateUser(Integer id,User user) {
		 User existingUser = getUserById(id);
		if(checkUserExistsById(id)) {
			existingUser.setName(user.getName());
		}
		return userRepository.save(existingUser);
	}
}
