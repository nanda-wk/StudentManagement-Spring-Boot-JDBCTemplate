package com.studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studentmanagement.dto.StudentRequestDTO;
import com.studentmanagement.dto.StudentResponseDTO;

@Repository
public class StudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertStudent(StudentRequestDTO dto) {
		int result = 0;
		String sql = "INSERT INTO student (student_id, student_name, class_name, register_date, status) VALUES (?, ?, ?, ?, ?)";
		result = jdbcTemplate.update(sql, dto.getStudentId(), dto.getStudentName(), dto.getClassName(),
				dto.getRegisterDate(), dto.getStatus());
		return result;
	}

	public int updateStudent(StudentRequestDTO dto) {
		int result = 0;
		String sql = "UPDATE student set student_name=?, class_name=?, register_date=?, status=? WHERE student_id=?";
		result = jdbcTemplate.update(sql, dto.getStudentName(), dto.getClassName(), dto.getRegisterDate(),
				dto.getStatus(), dto.getStudentId());
		return result;
	}

	public int deleteStudent(StudentRequestDTO dto) {
		int result = 0;
		String sql = "DELETE FROM student WHERE student_id=?";
		result = jdbcTemplate.update(sql, dto.getStudentId());
		return result;
	}

	
	public List<StudentResponseDTO> selectStudent(StudentRequestDTO dto) {
		List<StudentResponseDTO> list = new ArrayList<StudentResponseDTO>();
		String all = "SELECT * FROM student";
		String filter = "SELECT * FROM student WHERE student_id=? OR student_name=? OR class_name=?";

		if (!dto.getStudentId().equals("") || !dto.getStudentName().equals("") || !dto.getClassName().equals("")) {
			// Need to fix
			list = jdbcTemplate.query(filter,
					(rs, rowNum) -> new StudentResponseDTO(rs.getString("student_id"), rs.getString("student_name"),
							rs.getString("class_name"), rs.getDate("register_date"), rs.getString("status")),
					dto.getStudentId(), dto.getStudentName(), dto.getClassName());
		} else {
			// SelectAll query is correct
			list = jdbcTemplate.query(all,
					(rs, rowNum) -> new StudentResponseDTO(rs.getString("student_id"), rs.getString("student_name"),
							rs.getString("class_name"), rs.getDate("register_date"), rs.getString("status")));
		}
		return list;
	}
}
