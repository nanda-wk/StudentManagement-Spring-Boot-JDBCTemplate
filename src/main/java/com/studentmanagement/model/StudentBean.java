package com.studentmanagement.model;

import javax.validation.constraints.NotEmpty;

public class StudentBean {

	private String day, month, year;

	@NotEmpty(message = "StudentID can't be blank!")
	private String studentId;
	
	@NotEmpty(message = "Student Name can't be blank!")
	private String studentName;
	
	@NotEmpty(message = "ClassName can't be blank!")
	private String className;
	
	@NotEmpty(message = "Status can't be blank")
	private String status;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
