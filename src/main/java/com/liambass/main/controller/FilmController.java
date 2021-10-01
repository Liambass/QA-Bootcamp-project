package com.liambass.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liambass.main.domain.Film;
import com.liambass.main.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private FilmService service;
	
	public FilmController(FilmService service) {
		this.service =  service;
	}
	
	//Create
	@PostMapping("/create")
	public ResponseEntity<Film> create(@RequestBody Film f) {
		return new ResponseEntity<Film>(service.create(f),HttpStatus.CREATED);
	}
	
}
