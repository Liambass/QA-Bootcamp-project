package com.liambass.main.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Films")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String genre;
	private int year;
	private int duration;
	
	@JsonIgnore
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings;
	
	public Film(String title, String genre, int year, int duration) {
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.duration = duration;
	}
	
	public Film(Long id, String title, String genre, int year, int duration) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.duration = duration;
	}

	public Film() {
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
		return Objects.hash(bookings, duration, genre, id, title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(bookings, other.bookings) && duration == other.duration
				&& Objects.equals(genre, other.genre) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title) && year == other.year;
	}
	
	
	
}
