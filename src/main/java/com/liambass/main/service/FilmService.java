package com.liambass.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.repo.FilmRepo;

@Service
public class FilmService {

	private FilmRepo repo;
	
	@Autowired
	public FilmService(FilmRepo repo) {
		this.repo = repo;
	}
	
}
