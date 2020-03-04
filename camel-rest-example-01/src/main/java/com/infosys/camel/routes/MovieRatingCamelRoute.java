package com.infosys.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.infosys.camel.model.UserRating;

@Component
public class MovieRatingCamelRoute extends RouteBuilder {

	@Value("${app.camel.rest.component.name}")
	private String restComponent;

	@Value("${app.camel.rest.component.port}")
	private int port;

	@Value("${app.camel.rest.component.host}")
	private String host;
	
	@Value("${movie.rating.greeting.url}")
	private String greetingURL;
	
	@Value("${movie.rating.url}")
	private String ratingURL;
	
	@Override
	public void configure() {
		JacksonDataFormat userRating = new JacksonDataFormat(UserRating.class);
		JacksonDataFormat rating = new JacksonDataFormat(UserRating.class);
		
		setEnviornmentConfiguration();

		getGreeting();

		getRatingDataForUser(userRating);
		
		postRatingDataForUser(userRating,rating);
	}

	private void setEnviornmentConfiguration() {
		restConfiguration().component(restComponent).port(port).host(host).bindingMode(RestBindingMode.json);
	}
	
	private void getGreeting() {
		rest("/greeting").get("/{userId}").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.toD(greetingURL + "/${header.userId}?bridgeEndpoint=true");
	}

	private void getRatingDataForUser(JacksonDataFormat userRating) {
		rest().get("/rating/{userId}").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.toD(ratingURL + "/${header.userId}?bridgeEndpoint=true")
				.unmarshal(userRating);
	}
	
	private void postRatingDataForUser(JacksonDataFormat userRating, JacksonDataFormat rating) {
		rest().post("/rating").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
				.route().marshal(rating).to(ratingURL + "?bridgeEndpoint=true")
				.unmarshal(userRating);
	}

}

