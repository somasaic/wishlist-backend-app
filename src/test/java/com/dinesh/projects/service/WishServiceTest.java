package com.dinesh.projects.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dinesh.projects.entity.AppUser;
import com.dinesh.projects.entity.Wish;
import com.dinesh.projects.repository.AppUserRepository;
import com.dinesh.projects.repository.WishRepository;

@ExtendWith(MockitoExtension.class)
public class WishServiceTest {

	@Mock
	private WishRepository wishRepository;

	@Mock
	private AppUserRepository appUserRepository;

	@InjectMocks
	private WishService wishService;

	@Test
	public void testGetWishList() {
		AppUser appUser = new AppUser();
		appUser.setId(1L);
		when(appUserRepository.findByUsername("testUser")).thenReturn(appUser);
		Wish wish1 = new Wish();
		wish1.setId(1L);
		wish1.setUserId(1L);
		Wish wish2 = new Wish();
		wish2.setId(2L);
		wish2.setUserId(1L);
		List<Wish> wishes = Arrays.asList(wish1, wish2);
		when(wishRepository.findByUserId(1L)).thenReturn(wishes);
		List<Wish> result = wishService.getWishList("testUser");
		assertEquals(wishes.size(), result.size());
	}

	@Test
	public void testAddWish() {
		AppUser appUser = new AppUser();
		appUser.setId(1L);
		when(appUserRepository.findByUsername("testUser")).thenReturn(appUser);
		Wish wish = new Wish();
		wish.setWish("New Wish");
		when(wishRepository.save(wish)).thenReturn(wish);
		String message = wishService.addWish(wish, "testUser");
		assertEquals("wish saved successfully", message);
	}

	@Test
	public void testDeleteWish() {
		AppUser appUser = new AppUser();
		appUser.setId(1L);
		when(appUserRepository.findByUsername("testUser")).thenReturn(appUser);
		Wish wish = new Wish();
		wish.setId(1L);
		wish.setUserId(1L);
		when(wishRepository.findById(1L)).thenReturn(Optional.of(wish));
		String message = wishService.deleteWish(1L, "testUser");
		assertEquals("Wish item is deleted", message);
		verify(wishRepository).deleteById(1L);
	}

	@Test
	public void testDeleteWishNotFound() {
		AppUser appUser = new AppUser();
		appUser.setId(1L);
		when(appUserRepository.findByUsername("testUser")).thenReturn(appUser);
		when(wishRepository.findById(1L)).thenReturn(Optional.empty());
		String message = wishService.deleteWish(1L, "testUser");
		assertEquals("No such wish exists", message);
	}

	@Test
	public void testDeleteWishNotAllowed() {
		AppUser appUser = new AppUser();
		appUser.setId(1L);
		when(appUserRepository.findByUsername("testUser")).thenReturn(appUser);
		Wish wish = new Wish();
		wish.setId(1L);
		wish.setUserId(2L);
		when(wishRepository.findById(1L)).thenReturn(Optional.of(wish));
		String message = wishService.deleteWish(1L, "testUser");
		assertEquals("This wish cannot be deleted", message);
	}
}