package com.adobe.service;

import java.util.List;
import com.adobe.entity.User;
import com.adobe.exception.UserException;

public interface UserService {
	
	public User createUser(User user)throws UserException;

	public User getUserById(Long id)throws UserException;

	public User updateUser(Long id,User user)throws UserException;

	public void deleteUser(Long id)throws UserException;

	public List<User> getTotalUsers()throws UserException;

	public List<User> getTopActiveUsers()throws UserException;
}
