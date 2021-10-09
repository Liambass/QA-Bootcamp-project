package com.liambass.main.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.liambass.main.domain.Film;

public class BookingDTO {
	
	private Long id;
	private LocalDate date;
	private LocalTime time;
	private Film film;
	
	public BookingDTO(Long id, LocalDate date, LocalTime time, Film film) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.film = film;
	}

	public BookingDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	
	
	
	
	
	
	
}
	
	