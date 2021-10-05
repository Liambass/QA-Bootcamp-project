package com.liambass.main.dto;

public class FilmDTO {

	private Long id;
	private String title;
	private String genre;
	private int year;
	private int duration;

	public FilmDTO(String title, String genre, int year, int duration) {
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

}
