package com.dinesh.projects.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.dinesh.projects.entity.Role;
import com.dinesh.projects.repository.AppUserRepository;
import com.dinesh.projects.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {

	@Mock
	private AppUserRepository appUserRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private AppUserService appUserService;

	@Test
	public void testSignUp() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("username", "testUser");
		requestMap.put("password", "testPassword");

		when(appUserRepository.existsByUsername("testUser")).thenReturn(false);

		Role role = new Role();
		role.setName("USER");
		when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));

		String result = appUserService.signUp(requestMap);

		assertEquals("User Registered successfully", result);
	}

	@Test
	public void testSignUpUsernameExists() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("username", "existingUser");
		requestMap.put("password", "testPassword");

		when(appUserRepository.existsByUsername("existingUser")).thenReturn(true);

		String result = appUserService.signUp(requestMap);

		assertEquals("Username already exists", result);
	}

	@Test
	public void testLogin_SuccessfulAuthentication_ReturnsTrue() {
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "testUser");
		credentials.put("password", "testPassword");

		Authentication authentication = mock(Authentication.class);
		when(authentication.isAuthenticated()).thenReturn(true);
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenReturn(authentication);

		boolean result = appUserService.login(credentials);

		assertTrue(result);
	}
}