package com.studentmanagement.model;

import javax.validation.constraints.NotEmpty;

public class UserBean {

	@NotEmpty(message = "UserID can't be empty!")
	private String id;

	@NotEmpty(message = "User Name can't be empty!")
	private String name;

	@NotEmpty(message = "Password can't be empty!")
	private String password;
	
	@NotEmpty(message = "Password can't be empty!")
	private String conPwd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConPwd() {
		return conPwd;
	}

	public void setConPwd(String conPwd) {
		this.conPwd = conPwd;
	}
}
