package com.liambass.main.dto;

import java.util.List;
import java.util.Objects;

import com.liambass.main.domain.Booking;

public class FilmDTO {

	private Long id;
	private String title;
	private String genre;
	private int year;
	private int duration;
	private List<Booking> bookings;

	public FilmDTO(String title, String genre, int year, int duration) {
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.duration = duration;
	}

	public FilmDTO(Long id, String title, String genre, int year, int duration) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.duration = duration;
	}

	public FilmDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, genre, id, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmDTO other = (FilmDTO) obj;
		return duration == other.duration && Objects.equals(genre, other.genre) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title) && year == other.year;
	}

}
