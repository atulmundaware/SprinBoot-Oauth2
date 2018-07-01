package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	
	@GetMapping(value = "/")
	public String register(){
		return "Welcome";
	}


	@GetMapping(value = "/private")
	public String home(){
		return "Welcome come in....";
	}
	
}
