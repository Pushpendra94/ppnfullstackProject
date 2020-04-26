package com.example.demo;

public class UserNameAlreadyExitsResponse {
	private String userName;

	public UserNameAlreadyExitsResponse(String userName) {
		//super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
