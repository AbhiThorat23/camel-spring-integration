package io.javabrains.moviecatlogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatlogItem {
	String name;
	String desc;
	int rating;
}
