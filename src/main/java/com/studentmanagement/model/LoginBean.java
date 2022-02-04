package com.studentmanagement.model;

import javax.validation.constraints.NotEmpty;

public class LoginBean {

	@NotEmpty(message = "UserID can't be blank!")
	private String id;
	@NotEmpty(message = "Password can't be blank!")
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
