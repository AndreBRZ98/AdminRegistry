package com.nelr.adminregistry.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value="/login")
	
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
	}
	
	@PostMapping(value="/register")
	
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
	}

}
