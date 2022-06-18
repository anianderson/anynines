package com.anynines.assignment.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anynines.assignment.domain.GithubProject;

@Service
public class GithubProjectService {

	private RestTemplate restTemplate;

	public GithubProjectService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<GithubProject> findAllTopProjects() {
		String url = "https://github-trending-api.de.a9sapp.eu/";
		ResponseEntity<GithubProject[]> githubProjectResponse = restTemplate.getForEntity(URI.create(url),
				GithubProject[].class);
		if (githubProjectResponse.hasBody()) {
			return Arrays.asList(githubProjectResponse.getBody());
		}
		return Collections.emptyList();
	}

}
