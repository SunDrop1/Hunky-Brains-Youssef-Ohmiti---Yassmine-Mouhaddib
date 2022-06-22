package com.exam.examportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.examportal.model.User;
import com.exam.examportal.repo.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByusername(username);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found !!");
		}
		
		return user;
	}
		
}
