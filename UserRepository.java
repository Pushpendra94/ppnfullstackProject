package com.example.demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	User findByUserName(String userName);
	User getById(Long id);
	//Optional<User> findById(Long id);

}
