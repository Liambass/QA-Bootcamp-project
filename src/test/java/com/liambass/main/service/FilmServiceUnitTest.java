package com.liambass.main.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.liambass.main.domain.Film;
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
	
	@Test
	public void updateTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(of);
		Mockito.when(this.repo.saveAndFlush(f)).thenReturn(f);
		assertEquals(f, this.service.update(id, f));
	}
	
	@Test
	public void deleteTest() {
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		assertEquals(true, this.service.delete(id));
	}
	
	@Test
	public void findByTitleTest() {
		Mockito.when(this.repo.findByTitle("X")).thenReturn(fl);
		assertEquals(fl, this.service.findByTitle("X"));
	}
	
	@Test
	public void findByGenreTest() {
		Mockito.when(this.repo.findByGenre("X")).thenReturn(fl);
		assertEquals(fl, this.service.findByGenre("X"));
	}
	
	@Test
	public void findByYearTest() {
		Mockito.when(this.repo.findByYear(2000)).thenReturn(fl);
		assertEquals(fl, this.service.findByYear(2000));
	}
	
	@Test
	public void findByYearRangeTest() {
		Mockito.when(this.repo.findByYearRange(2000, 2020)).thenReturn(fl);
		assertEquals(fl, this.service.findByYearRange(2000, 2020));
	}
	
	@Test
	public void findByMinDurationTest() {
		Mockito.when(this.repo.findByMinDuration(100)).thenReturn(fl);
		assertEquals(fl, this.service.findByMinDuration(100));
	}
	
	@Test
	public void findByMaxDurationTest() {
		Mockito.when(this.repo.findByMaxDuration(100)).thenReturn(fl);
		assertEquals(fl, this.service.findByMaxDuration(100));
	}
} 
