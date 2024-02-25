package com.dinesh.projects.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dinesh.projects.entity.AppUser;
import com.dinesh.projects.entity.Role;
import com.dinesh.projects.repository.AppUserRepository;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailTest {

	@Mock
	private AppUserRepository repository;

	@InjectMocks
	private AppUserDetail appUserDetail;

	@Test
	public void testLoadUserByUsername() {
		// Mock data
		String username = "testUser";
		AppUser user = new AppUser();
		user.setUsername(username);
		user.setPassword("password");
		Role role = new Role();
		role.setName("USER");
		user.setRoles(Collections.singleton(role));

		// Configure repository mock
		Mockito.when(repository.findByUsername(username)).thenReturn(user);

		// Call the method to be tested
		UserDetails userDetails = appUserDetail.loadUserByUsername(username);

		// Verify the result
		assertEquals(username, userDetails.getUsername());
		assertEquals(user.getPassword(), userDetails.getPassword());
		assertEquals(1, userDetails.getAuthorities().size());
		assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER")));
	}

	@Test
	public void testLoadUserByUsernameNotFound() {
		String username = "nonExistingUser";
		Mockito.when(repository.findByUsername(username)).thenReturn(null);
		assertThrows(UsernameNotFoundException.class, () -> {
			appUserDetail.loadUserByUsername(username);
		});

	}
}