package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
    private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUserName(userName);
		if(user==null) {
			new UsernameNotFoundException("user not found");
		}
		return user;
	}
	@Transactional			
	public User loadUserById(Long id) {
		User user =userRepository.getById(id);
		if(user==null) {
			new UsernameNotFoundException("user not found");
		}
		return user;
	}

}
