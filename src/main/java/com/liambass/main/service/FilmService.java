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
	public Film readOne(Long id) {
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
	
	//FindByTitle
	public List<Film> findByTitle(String title) {
		return this.repo.findByTitle(title);
	}
	
	//FindByGenre
	public List<Film> findByGenre(String genre) {
		return this.repo.findByGenre(genre);
	}
	
	//FindByYear
	public List<Film> findByYear(int year) {
		return this.repo.findByYear(year);
	}
	
	//FindByYearRange
	public List<Film> findByYearRange(int year1, int year2) {
		return this.repo.findByYearRange(year1, year2);
	}
	
	//FindByMinDuration
	public List<Film> findByMinDuration(int duration) {
		return this.repo.findByMinDuration(duration);
	}
	
	//FindByMaxDuration
	public List<Film> findByMaxDuration(int duration) {
		return this.repo.findByMaxDuration(duration);
	}
}
