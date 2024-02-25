package com.dinesh.projects.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.projects.entity.AppUser;
import com.dinesh.projects.entity.Wish;
import com.dinesh.projects.repository.AppUserRepository;
import com.dinesh.projects.repository.WishRepository;

@Service
public class WishService {

	@Autowired
	private WishRepository wishRepo;
	@Autowired
	private AppUserRepository appUserRepository;

	public List<Wish> getWishList(String userName) {
		Long userId = appUserRepository.findByUsername(userName).getId();
		List<Wish> wishList = wishRepo.findByUserId(userId);
		return wishList;
	}

	public String addWish(Wish wish, String userName) {
		AppUser appUser = appUserRepository.findByUsername(userName);
		wish.setUserId(appUser.getId());
		wishRepo.save(wish);
		return "wish saved successfully";
	}

	public String deleteWish(Long id, String userName) {
		AppUser appUser = appUserRepository.findByUsername(userName);
		Optional<Wish> wish = wishRepo.findById(id);
		if (wish.isEmpty()) {
			return "No such wish exists";
		}
		if (appUser.getId() != wish.get().getUserId()) {
			return "This wish cannot be deleted";
		}
		wishRepo.deleteById(id);
		return "Wish item is deleted";
	}
}
