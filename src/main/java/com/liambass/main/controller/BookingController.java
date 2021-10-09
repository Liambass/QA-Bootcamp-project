package com.liambass.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liambass.main.dto.BookingDTO;
import com.liambass.main.dto.BookingWithFilmDTO;
import com.liambass.main.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService service;
	
	public BookingController(BookingService service) {
		this.service = service;
	}
	
	//Create
	@PostMapping("/create")
	public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO b) {
		return new ResponseEntity<BookingDTO>(service.create(b), HttpStatus.CREATED);
	}
	
	
	//ReadAll
	@GetMapping("/readAll")
	public ResponseEntity<List<BookingWithFilmDTO>> readAll() {
		return new ResponseEntity<List<BookingWithFilmDTO>>(this.service.readAll(), HttpStatus.OK);
	}
	
//	//ReadOne
//	@GetMapping("/readOne/{id}")
//	public ResponseEntity<BookingWithFilmDTO> readOne(@PathVariable Long id)	{
//		return new ResponseEntity<Booking>(this.service.readOne(id), HttpStatus.OK);
//	}
//	
//	//Update
//	@PutMapping("/update/{id}") 
//	public ResponseEntity<Booking> update(@PathVariable Long id, @RequestBody FilmDTO f) {
//		return new ResponseEntity<Booking>(this.service.update(id, f), HttpStatus.OK);
//	}
//	
	//Delete
	@DeleteMapping("/delete/{id}") 
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
    	service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
