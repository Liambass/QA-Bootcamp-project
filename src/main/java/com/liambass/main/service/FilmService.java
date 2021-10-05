package com.liambass.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.repo.FilmRepo;

import com.liambass.main.domain.Film;
import com.liambass.main.dto.FilmDTO;
import com.liambass.main.exceptions.IdNotFoundException;
import com.liambass.main.exceptions.InvalidYearRangeException;
import com.liambass.main.exceptions.NoMatchingRecordsException;

@Service
public class FilmService {

	private FilmRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public FilmService(FilmRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public FilmDTO mapToDTO(Film f) {
		return this.mapper.map(f,  FilmDTO.class);
	}
	
	public Film mapFromDTO(FilmDTO f) {
		return this.mapper.map(f,  Film.class);
	}
	
	//Create
	public FilmDTO create(FilmDTO f) {
		Film in = this.mapFromDTO(f);
		Film saved = this.repo.saveAndFlush(in);
		return this.mapToDTO(saved);
	}
	
	//Read All
	public List<FilmDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//Read by ID
	public FilmDTO readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(IdNotFoundException::new));
	}
	
	//Update
	public FilmDTO update(Long id, FilmDTO f) {
		Film up = this.mapFromDTO(f);
		Film exists = this.repo.findById(id).orElseThrow(IdNotFoundException::new);
		exists.setTitle(up.getTitle());
		exists.setGenre(up.getGenre());
		exists.setYear(up.getYear());
		exists.setDuration(up.getDuration());
		return this.mapToDTO(this.repo.saveAndFlush(exists));
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
	public List<FilmDTO> findByTitle(String title) {
		List<Film> list = this.repo.findByTitle(title);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//FindByGenre
	public List<FilmDTO> findByGenre(String genre) {
		List<Film> list = this.repo.findByGenre(genre);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//FindByYear
	public List<FilmDTO> findByYear(int year) {
		List<Film> list = this.repo.findByYear(year);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//FindByYearRange
	public List<FilmDTO> findByYearRange(int year1, int year2) {
		if (year1 > year2) throw new InvalidYearRangeException();
		List<Film> list = this.repo.findByYearRange(year1, year2);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//FindByMinDuration
	public List<FilmDTO> findByMinDuration(int duration) {
		List<Film> list = this.repo.findByMinDuration(duration);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//FindByMaxDuration
	public List<FilmDTO> findByMaxDuration(int duration) {
		List<Film> list = this.repo.findByMaxDuration(duration);
		if (list.isEmpty()) throw new NoMatchingRecordsException();
		return list.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
}
