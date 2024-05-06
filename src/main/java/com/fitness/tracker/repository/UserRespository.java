package com.fitness.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitness.tracker.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer>{
	
	public User findByUserName(String username);
	

}
