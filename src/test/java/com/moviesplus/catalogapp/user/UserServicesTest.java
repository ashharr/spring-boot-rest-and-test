package com.moviesplus.catalogapp.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.Optional;

import com.moviesplus.catalogapp.user.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	void testGetUserById() {
		
		// Creating new user
		User user = new User();
		user.setId(1000);
		user.setName("John Doe");
		
		// Making mock object return the user when service calls it internally
		// This allows you to control the behavior of the UserRepository 
		// during the test and ensure that the UserService receives the expected
		// data when it calls userRepository.findById(1000) internally.
		
		when(userRepository.findById(1000)).thenReturn(Optional.of(user));
		
		// Running the test
		User foundUser = userService.getUserById(1000);
		
		
		// Assessing the test
		assertEquals(1000, foundUser.getId());
		assertEquals("John Doe", foundUser.getName());

	}

	@Test
	void testGetUserByIdNotFound(){

		when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99999));
	}

	@Test
	void testSaveUser(){

		User user = new User();
		user.setName("John Doe");
		user.setId(1000);

		when(userRepository.save(any(User.class))).thenReturn(user);

		User savedUser = userService.addUser(user);

		assertEquals("John Doe",  savedUser.getName());
		assertEquals(1000,  savedUser.getId());


	}



}
