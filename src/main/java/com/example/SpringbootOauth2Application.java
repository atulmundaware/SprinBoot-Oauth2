package com.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import com.example.model.Role;
import com.example.model.User;
import com.example.respository.UserRepository;

@SpringBootApplication
public class SpringbootOauth2Application extends SpringBootServletInitializer{

	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2Application.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception{
		System.out.println(repo.count());
		if(repo.count() == 0){
			repo.save(new User("root","root",Arrays.asList(new Role("ADMIN"),new Role("USER"))));
		}
		
		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
	}
	
	
	
}
