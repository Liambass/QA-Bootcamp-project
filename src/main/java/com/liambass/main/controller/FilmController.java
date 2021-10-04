package com.liambass.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//Read all
	@GetMapping("/readAll")
	public ResponseEntity<List<Film>> readAll() {
		return new ResponseEntity<List<Film>>(service.readAll(), HttpStatus.OK);
	}
	
	//Read by ID
	@GetMapping("/readOne/{id}")
	public ResponseEntity<Film> readOne(@PathVariable Long id)	{
		return new ResponseEntity<Film>(this.service.readOne(id), HttpStatus.OK);
	}
	
	//Update
	@PutMapping("/update/{id}") 
	public ResponseEntity<Film> update(@PathVariable Long id, @RequestBody Film f) {
		return new ResponseEntity<Film>(this.service.update(id, f), HttpStatus.OK);
	}
	
	//Delete
    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
    	service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    //FindByTitle
    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<Film>> findByTitle(@PathVariable String title) {
    	return new ResponseEntity<List<Film>>(service.findByTitle(title), HttpStatus.OK);
    }
	
	//FindByGenre
    @GetMapping("/findByGenre/{genre}")
    public ResponseEntity<List<Film>> findByGenre(@PathVariable String genre) {
    	return new ResponseEntity<List<Film>>(service.findByGenre(genre), HttpStatus.OK);
    }
    
	//FindByYear
    @GetMapping("/findByYear/{year}")
    public ResponseEntity<List<Film>> findByYear(@PathVariable int year) {
    	return new ResponseEntity<List<Film>>(service.findByYear(year), HttpStatus.OK);
    }
    
	//FindByYearRange
    @GetMapping("/findByYearRange/{year1}/{year2}")
    public ResponseEntity<List<Film>> findByYearRange(@PathVariable int year1, @PathVariable int year2) {
    	return new ResponseEntity<List<Film>>(service.findByYearRange(year1, year2), HttpStatus.OK);
    }
    
	//FindByMinDuration
    @GetMapping("/findByMinDuration/{duration}")
    public ResponseEntity<List<Film>> findByMinDuration(@PathVariable int duration) {
    	return new ResponseEntity<List<Film>>(service.findByMinDuration(duration), HttpStatus.OK);
    }
    
	//FindByMaxDuration
    @GetMapping("/findByMaxDuration/{duration}")
    public ResponseEntity<List<Film>> findByMaxDuration(@PathVariable int duration) {
    	return new ResponseEntity<List<Film>>(service.findByMaxDuration(duration), HttpStatus.OK);
    }
    
} 
