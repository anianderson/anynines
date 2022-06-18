package com.anynines.assignment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anynines.assignment.domain.User;
import com.anynines.assignment.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{technology}/top")
	public ResponseEntity<List<User>> findAllTopUsersInGivenTech(
			@PathVariable(name = "technology", required = true) String technology) {
		List<User> users = this.userService.findAllTopUsersByTechnology(technology);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
