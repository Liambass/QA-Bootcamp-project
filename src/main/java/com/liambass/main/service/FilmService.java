package com.liambass.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.repo.FilmRepo;

import com.liambass.main.domain.Film;
import com.liambass.main.exceptions.IdNotFoundException;
import com.liambass.main.exceptions.InvalidYearRangeException;
import com.liambass.main.exceptions.NoMatchingRecordsException;

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
		return this.repo.findById(id).orElseThrow(IdNotFoundException::new);
	}
	
	//Update
	public Film update(Long id, Film f) {
		Film exists = this.repo.findById(id).orElseThrow(IdNotFoundException::new);
		exists.setTitle(f.getTitle());
		exists.setGenre(f.getGenre());
		exists.setYear(f.getYear());
		exists.setDuration(f.getDuration());
		return this.repo.saveAndFlush(exists);
	}
	
	//Delete
	public Boolean delete(Long id ) {
		if (!this.repo.existsById(id)) {
			throw new IdNotFoundException();
		};
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	
	//FindByTitle
	public List<Film> findByTitle(String title) {
		List<Film> list = this.repo.findByTitle(title);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
	
	//FindByGenre
	public List<Film> findByGenre(String genre) {
		List<Film> list = this.repo.findByGenre(genre);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
	
	//FindByYear
	public List<Film> findByYear(int year) {
		List<Film> list = this.repo.findByYear(year);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
	
	//FindByYearRange
	public List<Film> findByYearRange(int year1, int year2) {
		if (year1 > year2) {
			throw new InvalidYearRangeException();
		}
		List<Film> list = this.repo.findByYearRange(year1, year2);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
	
	//FindByMinDuration
	public List<Film> findByMinDuration(int duration) {
		List<Film> list = this.repo.findByMinDuration(duration);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
	
	//FindByMaxDuration
	public List<Film> findByMaxDuration(int duration) {
		List<Film> list = this.repo.findByMaxDuration(duration);
		if (list.isEmpty()) {
			throw new NoMatchingRecordsException();
		}
		return list;
	}
}
