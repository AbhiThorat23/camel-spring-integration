package io.javabrains.moviecatlogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatlogservice.models.Rating;
import io.javabrains.moviecatlogservice.models.UserRating;

@RestController
@RequestMapping("/catlog")
public class MovieCatlogController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/user")
	public String getCatlogs() {
		return "Hello";
	}

	@GetMapping("camel/{userId}")
	public String getWelcomeMsg(@PathVariable String userId) {
		return restTemplate.getForObject("http://localhost:8084/greeting/" + userId, String.class);
	}

	@GetMapping("/rating/{userId}")
	public UserRating getRatingUsingCamel(@PathVariable String userId) {
		return restTemplate.getForObject("http://localhost:8084/rating/" + userId, UserRating.class);
	}

	@PostMapping("/rating")
	public ResponseEntity<UserRating> createRatingUsingCamel(@RequestBody Rating rating) {
		return restTemplate.postForEntity("http://localhost:8084/rating/", rating, UserRating.class);
	}
}
