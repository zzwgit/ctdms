package com.jxufe.ctdms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController { 
	 
	@RequestMapping(value="login") 
	public String login(){ 
		System.out.println("login");
		return "";
	 } 
} 
