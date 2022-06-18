package com.anynines.assignment.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GithubProject {
	private String author;
	private String name;
	private String avatar;
	private String url;
	private String description;
	private String language;
	private String languageColor;
	private Long stars;
	private Long forks;
	private Long currentPeriodStars;
	private List<Developer> builtBy;
}
