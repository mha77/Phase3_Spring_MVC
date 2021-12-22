package com.simplilearn.Phase3_Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.Phase3_Spring.dao.DAO;
import com.simplilearn.Phase3_Spring.model.User;


@Controller
public class SearchUsers {

	@Autowired
	DAO dao;
	
	@PostMapping("/listUsers")
	public String listUser(Model model) {
		List<User> users =  dao.getUsers();
		model.addAttribute("users",users);
		
		return "listUser";		
	}
	
	
	@PostMapping("/searchUser")
	public String searchUser(@ModelAttribute("user") User user, Model model) {
		String userName = user.getName();
		String u =  dao.searchUser(userName);
		
		if(u.equals(userName)) {
			model.addAttribute("userFound", "User " + user.getName() +" found");
		}else {
			model.addAttribute("userFound", "User " + user.getName() +" not found");
		}
		//model.addAttribute("users",u);
		
		return "welcome";		
	}
}
