package com.exam.examportal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examportal.model.User;



@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	public User findByusername(String username);

}
