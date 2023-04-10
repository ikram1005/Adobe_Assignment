package com.adobe.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.entity.User;
import com.adobe.exception.UserException;
import com.adobe.repository.UserRepository;
import com.adobe.service.UserService;

@Service
public class UserImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws UserException {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserException("User with this email already exists.");
		}
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) throws UserException {
		return userRepository.findById(id).orElseThrow(() -> new UserException("User not found with this id."));
	}

	@Override
	public User updateUser(Long id, User user) throws UserException {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return userRepository.save(user);
		} else
			throw new UserException("User not found with this id.");
	}

	@Override
	public void deleteUser(Long id) throws UserException {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new UserException("User not found with this id.");
		}
	}

	@Override
	public List<User> getTotalUsers() throws UserException {
		List<User> users = userRepository.findAll();
		if (users.size() > 0)
			return users;
		else
			throw new UserException("Users not found");
	}

	@Override
	public List<User> getTopActiveUsers() {
		List<User> users = userRepository.findAll();
		users.sort((u1, u2) -> Integer.compare(u2.getPosts().size(), u1.getPosts().size()));
		return users.stream().limit(5).collect(Collectors.toList());
	}

}
