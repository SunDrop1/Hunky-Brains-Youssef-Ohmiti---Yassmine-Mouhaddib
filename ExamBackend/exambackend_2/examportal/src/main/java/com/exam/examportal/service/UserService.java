package com.exam.examportal.service;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exam.examportal.model.User;
import com.exam.examportal.model.UserRole;



@Component
public interface UserService {
	
		//Creating user
		public User createUser(User user, Set<UserRole> userRoles) throws Exception;
		
		//get user by username
		public User getUser(String username);
		
		//delete user by Id
		public void deleteUser(Long id);
		
}
