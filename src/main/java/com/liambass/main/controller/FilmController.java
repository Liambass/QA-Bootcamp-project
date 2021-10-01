package com.liambass.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liambass.main.service.FilmService;

@RestController
@RequestMapping("/films")
public class FilmController {

	@Autowired
	private FilmService service;
	
	public FilmController(FilmService service) {
		this.service =  service;
	}
	
}
