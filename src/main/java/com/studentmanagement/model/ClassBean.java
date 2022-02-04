package com.studentmanagement.model;

import javax.validation.constraints.NotEmpty;

public class ClassBean {

	@NotEmpty(message = "Class ID can't be empty!")
	private String id;
	
	@NotEmpty(message = "Class Name can't be empty!")
	private String name;

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
}
