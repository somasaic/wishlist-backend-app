package com.dinesh.projects.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dinesh.projects.service.AppUserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AppUserControllerTest {

	@InjectMocks
	private AppUserController appUserController;
	@Mock
	private AppUserService appUserService;

	@Test
	public void testSignUp() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("username", "testUser");
		requestMap.put("password", "testPassword");

		when(appUserService.signUp(requestMap)).thenReturn("User signed up successfully");

		ResponseEntity<String> response = appUserController.signUp(requestMap);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"message\":\"User signed up successfully\"}", response.getBody());
		verify(appUserService, times(1)).signUp(any());
	}

	@Test
	public void testSignUpUserAlreadyExists() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("username", "testUser");
		requestMap.put("password", "testPassword");

		when(appUserService.signUp(requestMap)).thenReturn("User already exists");

		ResponseEntity<String> response = appUserController.signUp(requestMap);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"message\":\"User already exists\"}", response.getBody());
	}

	@Test
	public void testLoginSuccess() {
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "testUser");
		credentials.put("password", "testPassword");

		when(appUserService.login(credentials)).thenReturn(true);

		ResponseEntity<String> response = appUserController.login(credentials);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"message\":\"logged in successfully\"}", response.getBody());
	}

	@Test
	public void testLoginFailure() {
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "testUser");
		credentials.put("password", "testPassword");

		when(appUserService.login(credentials)).thenReturn(false);

		ResponseEntity<String> response = appUserController.login(credentials);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
}
