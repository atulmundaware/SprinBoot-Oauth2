package com.example.controller;

import com.example.vo.LoginRequest;
import com.example.vo.TokenResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
public class LoginController {


	@Value("oauth.client")
	String client;

	@Value("oauth.secret")
	String secret;

	private RestTemplate restTemplate = new RestTemplate();

	@PostMapping("/login")
	public TokenResponse loginUser(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
		String baseUrl = String.format("%s://%s:%d/oauth/token",request.getScheme(),  request.getServerName(), request.getServerPort());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type","password");
		map.add("username",loginRequest.getUsername());
		map.add("password",loginRequest.getPassword());

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, getHeader());

		ResponseEntity<TokenResponse> response =
				restTemplate.exchange(baseUrl,
						HttpMethod.POST,
						entity,
						TokenResponse.class);

		return response.getBody();
	}
	
	@GetMapping(value = "/")
	public String register(){
		return "Welcome";
	}


	@GetMapping(value = "/private")
	public String home(){
		return "Welcome come in....";
	}

	private HttpHeaders getHeader() {
		String auth = client + ":" + secret;
		byte[] encodedAuth = Base64.encodeBase64(
				auth.getBytes(StandardCharsets.US_ASCII) );
		String authHeader = "Basic " + new String( encodedAuth );
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", authHeader);
		return headers;
	}
	
}
