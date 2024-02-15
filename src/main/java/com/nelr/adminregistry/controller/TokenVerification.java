package com.nelr.adminregistry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/verification")
public class TokenVerification {
	
	@GetMapping
	public ResponseEntity<Boolean> tokenVerification(){
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
