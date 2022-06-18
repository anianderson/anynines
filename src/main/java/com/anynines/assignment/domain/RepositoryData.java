package com.anynines.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepositoryData {
	private String name;
	private String description;
	private String url;
}
