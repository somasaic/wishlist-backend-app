package com.dinesh.projects.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dinesh.projects.service.AppUserService;

@RestController
@RequestMapping("/api/user")
public class AppUserController {
	@Autowired
	private AppUserService appUserService;

	@PostMapping(path = { "/signUp" })
	public ResponseEntity<String> signUp(@RequestBody Map<String, String> requestMap) {
		String message = appUserService.signUp(requestMap);
		return ResponseEntity.ok("{\"message\":\"" + message + "\"}");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
		Boolean flag = appUserService.login(credentials);
		if (flag) {
			return ResponseEntity.ok("{\"message\":\"logged in successfully\"}");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
