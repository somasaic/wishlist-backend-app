package com.dinesh.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.projects.entity.Wish;
import com.dinesh.projects.service.WishService;

@RestController
@RequestMapping("/api/wishlists")
public class WishController {
	@Autowired
	private WishService wishService;

	@GetMapping(path = "")
	public ResponseEntity<List<Wish>> getWishlist(Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		List<Wish> wishlist = wishService.getWishList(authentication.getName());
		return ResponseEntity.ok(wishlist);
	}

	@PostMapping(path = "")
	public ResponseEntity<String> addWishList(@RequestBody Wish wish, Authentication authentication) {
		String message = wishService.addWish(wish, authentication.getName());
		return ResponseEntity.ok("{\"message\":\"" + message + "\"}");
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<String> deleteWish(@PathVariable Long id, Authentication authentication) {
		String message = wishService.deleteWish(id, authentication.getName());
		return ResponseEntity.ok(message);
	}

}
