package com.anynines.assignment.service;

import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anynines.assignment.domain.GithubProject;
import com.anynines.assignment.domain.User;

@Service
public class UserService {

	private RestTemplate restTemplate;

	private GithubProjectService githubProjectService;

	public UserService(RestTemplate restTemplate, GithubProjectService githubProjectService) {
		this.restTemplate = restTemplate;
		this.githubProjectService = githubProjectService;
	}

	public List<User> findAllUsersByTechnology(String technology) {
		String url = "https://github-trending-api.de.a9sapp.eu/developers/"
				+ URLEncoder.encode(technology.trim().toLowerCase());
		ResponseEntity<User[]> userResponse = restTemplate.getForEntity(URI.create(url), User[].class);
		if (userResponse.hasBody()) {
			return Arrays.asList(userResponse.getBody());
		}
		return Collections.emptyList();
	}

	public List<User> findAllTopUsersByTechnology(String technology) {
		List<GithubProject> projects = githubProjectService.findAllTopProjects();
		projects = projects.stream()
				.filter(project -> project.getLanguage() != null && project.getLanguage().equalsIgnoreCase(technology))
				.collect(Collectors.toList());
		Set<String> developers = new HashSet<>();
		projects.stream().forEach(project -> {
			project.getBuiltBy().stream().forEach(developer -> {
				developers.add(developer.getUsername());
			});
		});
		List<User> users = this.findAllUsersByTechnology(technology).stream()
				.filter(user -> developers.contains(user.getUsername())).collect(Collectors.toList());
		return users;
	}

}
