package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
    
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			//UserName has to be unique(exception)
			//make sure that password and confirmPassword match
			//We don't persist or show confirmPassword
			newUser.setUserName(newUser.getUserName());
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
			
		}catch(Exception e) {
			throw new UserNameAlreadyExistsException("UserName "+newUser.getUserName()+" already Exists");
			
			
		}

		
	}
}
