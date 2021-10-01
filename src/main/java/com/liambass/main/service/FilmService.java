package com.liambass.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.repo.FilmRepo;

import com.liambass.main.domain.Film;

@Service
public class FilmService {

	private FilmRepo repo;
	
	@Autowired
	public FilmService(FilmRepo repo) {
		this.repo = repo;
	}
	
	//Create
	public Film create(Film f) {
		return this.repo.saveAndFlush(f);
	}
	
	//Read All
	public List<Film> readAll() {
		return this.repo.findAll();
	}
	
	//Read by ID
	public Film readCar(Long id) {
		return this.repo.findById(id).get();
	}
	
	//Update
	public Film update(Long id, Film f) {
		Film exists = this.repo.findById(id).get();
		exists.setTitle(f.getTitle());
		exists.setGenre(f.getGenre());
		exists.setYear(f.getYear());
		exists.setDuration(f.getDuration());
		return this.repo.saveAndFlush(exists);
	}
	
	//Delete
	public Boolean delete(Long id ) {
		Boolean bool = this.repo.existsById(id);
		this.repo.deleteById(id);
		return bool;
	}
	
}
