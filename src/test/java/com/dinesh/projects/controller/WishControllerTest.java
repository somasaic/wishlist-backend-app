package com.dinesh.projects.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.dinesh.projects.entity.Wish;
import com.dinesh.projects.service.WishService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class WishControllerTest {
	@InjectMocks
	private WishController wishController;
	@Mock
	private WishService wishService;

	@Test
	public void testGetWishlist() {
		List<Wish> wishlist = Arrays.asList(new Wish("Wish 1"), new Wish("Wish 2"));
		Authentication authentication = mock(Authentication.class);
		when(wishService.getWishList(authentication.getName())).thenReturn(wishlist);
		ResponseEntity<List<Wish>> response = wishController.getWishlist(authentication);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(wishlist, response.getBody());
	}

	@Test
	public void testAddWishlist() {
		Wish wish = new Wish("New Wish");
		Authentication authentication = mock(Authentication.class);
		when(wishService.addWish(wish, authentication.getName())).thenReturn("wish saved successfully");
		ResponseEntity<String> response = wishController.addWishList(wish, authentication);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("{\"message\":\"wish saved successfully\"}", response.getBody());
	}

	@Test
	public void testDeleteWishlist() {
		Long id = 1L;
		String message = "Wish deleted successfully";
		Authentication authentication = mock(Authentication.class);
		when(wishService.deleteWish(id, authentication.getName())).thenReturn(message);
		ResponseEntity<String> response = wishController.deleteWish(id, authentication);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(message, response.getBody());
	}

}
