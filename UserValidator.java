package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		User user=(User) object;
		if(user.getPassword().length()<6 && user.getPassword().length()>=1 ) {
			errors.rejectValue("Password", "Length=", "Password must be atleast 6 charcters");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("Password","and" , "confirm password should be match");
			
		}
		
	}

}
