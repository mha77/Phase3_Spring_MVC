package com.simplilearn.Phase3_Spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DAO {
	
	@Autowired
	NamedParameterJdbcTemplate NPjdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String authenticate(String user, String password) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user", user);
		return NPjdbcTemplate.queryForObject("select password from user where name=:user", namedParameters, String.class);
		
	}
	
	public int changePw(String user, String password) {
		int result = jdbcTemplate.update("update user set password = ? where name = ?", password, user);
		return result;
		
	}

}
