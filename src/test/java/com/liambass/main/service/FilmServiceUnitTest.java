package com.liambass.main.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.liambass.main.domain.Film;
import com.liambass.main.dto.FilmDTO;
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
	@Mock
	private ModelMapper mapper;
	
	private Film f = new Film("Title", "Genre", 2021, 100);
	private FilmDTO fd = new FilmDTO("Title", "Genre", 2021, 100);
	private List<Film> fl = List.of(f);
	private List<FilmDTO> fld = List.of(fd);
	private Optional<Film> of = Optional.of(f);
	private Long id = 1L;
	private List<Film> empty = Collections.emptyList();
	
	@Test
	public void createTest() {
		Mockito.when(this.mapper.map(fd, Film.class)).thenReturn(f);
		Mockito.when(this.repo.saveAndFlush(f)).thenReturn(f);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fd, this.service.create(fd));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(f);
		Mockito.verify(this.mapper, Mockito.times(2)).map(any(), any());
	}
	
	@Test
	public void readAllTest() {
		Mockito.when(this.repo.findAll()).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.readAll());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test
	public void readOneTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(of);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fd, this.service.readOne(id));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = IdNotFoundException.class)
	public void readOneFailTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.empty());
		this.service.readOne(id);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.mapper, Mockito.never()).map(any(), any());
	}
	
	@Test
	public void updateTest() {
		Mockito.when(this.mapper.map(fd, Film.class)).thenReturn(f);
		Mockito.when(this.repo.findById(id)).thenReturn(of);
		Mockito.when(this.repo.saveAndFlush(f)).thenReturn(f);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fd, this.service.update(id, fd));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(f);
		Mockito.verify(this.mapper, Mockito.times(2)).map(any(), any());
		
	}
	
	@Test(expected = IdNotFoundException.class)
	public void updateFailTest() {
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.empty());
		this.service.update(id, fd);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.mapper, Mockito.times(1)).map(any(), any());
		Mockito.verify(this.repo, Mockito.never()).saveAndFlush(f);
	}	
		
	@Test
	public void deleteTest() {
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		assertEquals(true, this.service.delete(id));
		
		Mockito.verify(this.repo, Mockito.times(2)).existsById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test(expected = IdNotFoundException.class)
	public void deleteFailTest() {
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		this.service.delete(id);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		Mockito.verify(this.repo, Mockito.never()).deleteById(id);
	}
	
	@Test
	public void findByTitleTest() {
		Mockito.when(this.repo.findByTitle("X")).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByTitle("X"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByTitle("X");
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByTitleFailTest() {
		Mockito.when(this.repo.findByTitle("X")).thenReturn(empty);
		this.service.findByTitle("X");
		
		Mockito.verify(this.repo, Mockito.times(1)).findByTitle("X");
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	@Test
	public void findByGenreTest() {
		Mockito.when(this.repo.findByGenre("X")).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByGenre("X"));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByGenre("X");
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByGenreFailTest() {
		Mockito.when(this.repo.findByGenre("X")).thenReturn(empty);
		this.service.findByGenre("X");
		
		Mockito.verify(this.repo, Mockito.times(1)).findByGenre("X");
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	@Test
	public void findByYearTest() {
		Mockito.when(this.repo.findByYear(2000)).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByYear(2000));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByYear(2000);
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByYearFailTest() {
		Mockito.when(this.repo.findByYear(2000)).thenReturn(empty);
		this.service.findByYear(2000);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByYear(2000);
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	@Test
	public void findByYearRangeTest() {
		Mockito.when(this.repo.findByYearRange(2000, 2020)).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByYearRange(2000, 2020));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByYearRange(2000, 2020);
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByYearRangeFailTest() {
		Mockito.when(this.repo.findByYearRange(2000, 2020)).thenReturn(empty);
		this.service.findByYearRange(2000, 2020);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByYearRange(2000, 2020);
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	@Test(expected = InvalidYearRangeException.class)
	public void findByYearRangeInvalidTest() {
		this.service.findByYearRange(2000, 1999);
		
		Mockito.verify(this.repo, Mockito.never()).findByYearRange(2000, 2020);
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	
	@Test
	public void findByMinDurationTest() {
		Mockito.when(this.repo.findByMinDuration(100)).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByMinDuration(100));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByMinDuration(100);
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByMinDurationFailTest() {
		Mockito.when(this.repo.findByMinDuration(100)).thenReturn(empty);
		this.service.findByMinDuration(100);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByMaxDuration(100);
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
	
	@Test
	public void findByMaxDurationTest() {
		Mockito.when(this.repo.findByMaxDuration(100)).thenReturn(fl);
		Mockito.when(this.mapper.map(f, FilmDTO.class)).thenReturn(fd);
		assertEquals(fld, this.service.findByMaxDuration(100));
		
		Mockito.verify(this.repo, Mockito.times(1)).findByMaxDuration(100);
		Mockito.verify(this.mapper, Mockito.times(1)).map(f, FilmDTO.class);
	}
	
	@Test(expected = NoMatchingRecordsException.class)
	public void findByMaxDurationFailTest() {
		Mockito.when(this.repo.findByMaxDuration(100)).thenReturn(empty);
		this.service.findByMaxDuration(100);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByMaxDuration(100);
		Mockito.verify(this.mapper, Mockito.never()).map(f, FilmDTO.class);
	}
}  
