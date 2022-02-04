package com.studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studentmanagement.dto.UserRequestDTO;
import com.studentmanagement.dto.UserResponseDTO;

@Repository
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insertUser(UserRequestDTO dto) {
		int i = 0;
		String sql = "INSERT INTO user (id, name, password) VALUES (?, ?, ?)";
		i = jdbcTemplate.update(sql, dto.getId(), dto.getName(), dto.getPassword());
		return i;
	}

	public int updateUser(UserRequestDTO dto) {
		int i = 0;
		String sql = "UPDATE user SET name=?, password=? WHERE id=?";
		i = jdbcTemplate.update(sql, dto.getName(), dto.getPassword(), dto.getId());
		return i;
	}

	public int deleteUser(UserRequestDTO dto) {
		int i = 0;
		String sql = "DELETE FROM user WHERE id=?";
		i = jdbcTemplate.update(sql, dto.getId());
		return i;
	}

	public List<UserResponseDTO> selectUser(UserRequestDTO dto) {
		List<UserResponseDTO> list = new ArrayList<UserResponseDTO>();
		String all = "SELECT * FROM user";
		String bt = "SELECT * FROM user WHERE id=? OR name=?";
		
		if(!dto.getId().equals("") || !dto.getName().equals("")) {
			list = jdbcTemplate.query(bt, (rs, rowNum) -> new UserResponseDTO(rs.getString("id"), rs.getString("name"),
					rs.getString("password")), dto.getId(), dto.getName());
		} else {
			list = jdbcTemplate.query(all, (rs, rowNum) -> new UserResponseDTO(rs.getString("id"), rs.getString("name"),
					rs.getString("password")));
		}

		return list;
	}
}
