package com.infosys.camel.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.infosys.camel.model.UserRating;

@Component
public class UserRatingBean {

	@Autowired
	private RestTemplate template;

	public UserRating getuserRating() {
		return template.getForObject("http://localhost:8092/ratings/users/foo", UserRating.class);
	}
}
