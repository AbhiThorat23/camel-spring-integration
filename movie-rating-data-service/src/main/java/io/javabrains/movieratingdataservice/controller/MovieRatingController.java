package io.javabrains.movieratingdataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.movieratingdataservice.models.Rating;
import io.javabrains.movieratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class MovieRatingController {

	private static List<Rating> ratings = getUserRatingList();
	
	@GetMapping("greeting/{userId}")
	public String getGreeting(@PathVariable("userId") String userId) {
		return "Hello " + userId + " from rating service";
	}

	@GetMapping("users/{userId}")
	public UserRating getRating(@PathVariable("userId") String userId) {
		return new UserRating(ratings);
	}

	@PostMapping("users")
	public UserRating createRating(@RequestBody Rating rating) {
		ratings.add(rating);
		return new UserRating(ratings);
	}

	private static List<Rating> getUserRatingList() {
		List<Rating> ratings = new ArrayList<>();
		ratings.add(new Rating("1234", 4));
		ratings.add(new Rating("5678", 3));
		return ratings;
	}

}
