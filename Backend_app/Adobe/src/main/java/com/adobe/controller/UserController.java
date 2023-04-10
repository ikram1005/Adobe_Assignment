package com.adobe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.entity.User;
import com.adobe.exception.UserException;
import com.adobe.service.UserService;

@RestController
public class UserController {

	@Autowired
    private UserService userService;

    // POST /users: Create a new user.
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws UserException {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // GET /users/{id}: Retrieve a user by id.
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) throws UserException {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // PUT /users/{id}: Update a user's name or bio by id.
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody User user) throws UserException {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // DELETE /users/{id}: Delete a user by id.
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) throws UserException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET /analytics/users: Retrieve the total number of users.
    @GetMapping("/analytics/users")
    public ResponseEntity<List<User>> getTotalUsers() throws UserException {
        List<User> totalUsers = userService.getTotalUsers();
        return new ResponseEntity<List<User>>(totalUsers,HttpStatus.OK);
    }

    // GET /analytics/users/top-active: Retrieve the top 5 most active users, based on the number of posts.
    @GetMapping("/analytics/users/top-active")
    public ResponseEntity<List<User>> getTopActiveUsers() throws UserException {
        List<User> topActiveUsers = userService.getTopActiveUsers();
        return new ResponseEntity<List<User>>(topActiveUsers,HttpStatus.OK);
    }
}
