package com.liambass.main.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class BookingWithFilmDTO {

	private Long id;
	private LocalDate date;
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime time;
	private String title;
	
	public BookingWithFilmDTO(Long id, LocalDate date, LocalTime time, String title) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.title = title;
	}
	public BookingWithFilmDTO() {
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	
}
