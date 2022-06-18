package com.anynines.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User {

	private String username;
	private String name;
	private String url;
	private String avatar;
	private RepositoryData repo;

}
