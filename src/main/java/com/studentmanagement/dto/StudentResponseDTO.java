package com.studentmanagement.dto;

import java.util.Date;

public class StudentResponseDTO {
	private String studentId, studentName, className, status;
	private Date registerDate;

	
	public StudentResponseDTO(String studentId, String studentName, String className, Date registerDate,
			String status) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.className = className;
		this.registerDate = registerDate;
		this.status = status;
	}

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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
