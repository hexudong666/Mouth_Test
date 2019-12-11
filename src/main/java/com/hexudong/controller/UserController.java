package com.hexudong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hexudong.eitity.User;
import com.hexudong.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("home")
	private String getList() {
		return "user/home";
	}
	
	@RequestMapping("login")
	private String login(Model model) {
//		model.addAttribute("user", new LoginUser());
		return "user/login";
	}
	
	@RequestMapping("dl")
	private String dl() {
//		判断
		return "redirect:/list";
	}
	
}
