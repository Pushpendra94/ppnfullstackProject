package com.example.demo;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message="Username can not be blank")
	private String userName;
	@NotBlank(message="Password can not be blank")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
