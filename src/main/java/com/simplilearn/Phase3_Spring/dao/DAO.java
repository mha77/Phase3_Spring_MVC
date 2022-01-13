package com.simplilearn.Phase3_Spring.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.simplilearn.Phase3_Spring.model.Product;
import com.simplilearn.Phase3_Spring.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	public List<User> getUsers(){
		return jdbcTemplate.query("select * from user", new RowMapper<User> () {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setName(rs.getString(1));
				return u;
			}
		});	
	}

	public String searchUser(String user) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("user", user);
		String us = null;
		try {
			us = NPjdbcTemplate.queryForObject("select name from user where name=:user", namedParameters, String.class);
		}catch(Exception ex){
			System.out.println("Empty Resultset");
		
		}
	
		if(us != null && !us.isEmpty() && us.equals(user) ){
			return us;
		}else {
			return "not found";
		}
	}
	
	public List<Product> getProducts(){
		
		return jdbcTemplate.query("select * from product", new RowMapper<Product> () {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setCategory(rs.getString(3));
				return p;
			}
		});	
	}
	
	public int setProduct(String name, String category) {
		
		int result = jdbcTemplate.update("insert into product (name, category) values(?, ?)", name, category);
		return result;
		
	}
	
	public int delProduct(String name, String category) {
		
		int result = jdbcTemplate.update("delete from product where  name =? and category=?", name, category);
		return result;
		
	}
}
