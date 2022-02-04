package com.studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studentmanagement.dto.ClassRequestDTO;
import com.studentmanagement.dto.ClassResponseDTO;

@Repository
public class ClassDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertClass(ClassRequestDTO dto) {
		int i = 0;
		String sql = "INSERT INTO class (id, name) VALUES (?, ?)";
		i = jdbcTemplate.update(sql, dto.getId(), dto.getName());
		return i;
	}

	public List<ClassResponseDTO> selectClass(ClassRequestDTO dto) {
		List<ClassResponseDTO> list = new ArrayList<ClassResponseDTO>();
		String s = "SELECT * FROM class";
		String id = "SELECT * FROM class WHERE id=? OR name=?";

		if (!dto.getId().equals("") || !dto.getName().equals("")) {
			list = jdbcTemplate.query(id,
					(rs, rowNum) -> new ClassResponseDTO(rs.getString("id"), rs.getString("name")), dto.getId(),
					dto.getName());
		}
		list = jdbcTemplate.query(s, (rs, rowNum) -> new ClassResponseDTO(rs.getString("id"), rs.getString("name")));

		return list;
	}
}
