package com.liambass.main.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime time;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "film_id", nullable = false)
	private Film film;

	public Booking() {
	}

	public Booking(LocalDate date, LocalTime time) {
		super();
		this.date = date;
		this.time = time;
	}

	public Booking(Long id, LocalDate date, LocalTime time) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
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

	@Override
	public int hashCode() {
		return Objects.hash(date, film, id, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(date, other.date) && Objects.equals(film, other.film) && Objects.equals(id, other.id)
				&& Objects.equals(time, other.time);
	}
	
	
	
}
