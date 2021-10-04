package com.liambass.main.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.liambass.main.domain.Film;
import com.liambass.main.exceptions.IdNotFoundException;
import com.liambass.main.exceptions.InvalidYearRangeException;
import com.liambass.main.exceptions.NoMatchingRecordsException;
import com.liambass.main.repo.FilmRepo;

@RunWith(MockitoJUnitRunner.class)
public class FilmServiceUnitTest {

	@InjectMocks
	private FilmService service;
	
	@Mock
	private FilmRepo repo;
	
	private Film f = new Film("Title", "Genre", 2021, 100);
	private List<Film> fl = List.of(f);
	private Optional<Film> of = Optional.of(f);
	private Long id = 1L;
	private List<Film> empty = Collections.emptyList();
	
	@Test
	public void createTest() {
		Mockito.when(this.repo.saveAndFlush(f)).thenReturn(f);
		assertEquals(f, this.service.create(f));
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.repo.findAll()).thenReturn(fl);
		assertEquals(fl, this.service.readAll());
	}
	
	@Test
	public void readOneTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(of);
		assertEquals(f, this.service.readOne(id));
	}
	
	@Test(expected = IdNotFoundException.class)
	public void readOneFailTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.empty());
		this.service.readOne(id);
	}
	
	@Test
	public void updateTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(of);
		Mockito.when(this.repo.saveAndFlush(f)).thenReturn(f);
		assertEquals(f, this.service.update(id, f));
	}
	
	@Test(expected = IdNotFoundException.class)
	public void updateFailTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.empty());
		this.service.update(id, f);
	}	
		
	@Test
	public void deleteTest() {
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		assertEquals(true, this.service.delete(id));
	}
	
	@Test(expected = IdNotFoundException.class)
	public void deleteFailTest() {
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		this.service.delete(id);
	}
	
	@Test
	public void findByTitleTest() {
		Mockito.when(this.repo.findByTitle("X")).thenReturn(fl);
		assertEquals(fl, this.service.findByTitle("X"));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByTitleFailTest() {
		Mockito.when(this.repo.findByTitle("X")).thenReturn(empty);
		this.service.findByTitle("X");
	}
	
	@Test
	public void findByGenreTest() {
		Mockito.when(this.repo.findByGenre("X")).thenReturn(fl);
		assertEquals(fl, this.service.findByGenre("X"));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByGenreFailTest() {
		Mockito.when(this.repo.findByGenre("X")).thenReturn(empty);
		this.service.findByGenre("X");
	}
	
	@Test
	public void findByYearTest() {
		Mockito.when(this.repo.findByYear(2000)).thenReturn(fl);
		assertEquals(fl, this.service.findByYear(2000));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByYearFailTest() {
		Mockito.when(this.repo.findByYear(2000)).thenReturn(empty);
		this.service.findByYear(2000);
	}
	
	@Test
	public void findByYearRangeTest() {
		Mockito.when(this.repo.findByYearRange(2000, 2020)).thenReturn(fl);
		assertEquals(fl, this.service.findByYearRange(2000, 2020));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByYearRangeFailTest() {
		Mockito.when(this.repo.findByYearRange(2000, 2020)).thenReturn(empty);
		this.service.findByYearRange(2000, 2020);
	}
	
	@Test(expected = InvalidYearRangeException.class)
	public void findByYearRangeInvalidTest() {
		this.service.findByYearRange(2000, 1999);
	}
	
	
	@Test
	public void findByMinDurationTest() {
		Mockito.when(this.repo.findByMinDuration(100)).thenReturn(fl);
		assertEquals(fl, this.service.findByMinDuration(100));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByMinDurationFailTest() {
		Mockito.when(this.repo.findByMinDuration(100)).thenReturn(empty);
		this.service.findByMinDuration(100);
	}
	
	@Test
	public void findByMaxDurationTest() {
		Mockito.when(this.repo.findByMaxDuration(100)).thenReturn(fl);
		assertEquals(fl, this.service.findByMaxDuration(100));
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByMaxDurationFailTest() {
		Mockito.when(this.repo.findByMaxDuration(100)).thenReturn(empty);
		this.service.findByMaxDuration(100);
	}
} 
