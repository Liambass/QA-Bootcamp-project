package com.liambass.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.domain.Booking;
import com.liambass.main.dto.BookingDTO;
import com.liambass.main.dto.BookingWithFilmDTO;
import com.liambass.main.exceptions.IdNotFoundException;
import com.liambass.main.repo.BookingRepo;

@Service
public class BookingService {
	
	private BookingRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public BookingService(BookingRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public List<BookingWithFilmDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToBFDTO).collect(Collectors.toList());
	}
	
	private BookingWithFilmDTO mapToBFDTO(Booking booking) {
		BookingWithFilmDTO dto = new BookingWithFilmDTO();
		
		dto.setId(booking.getId());
		dto.setDate(booking.getDate());
		dto.setTime(booking.getTime());
		dto.setTitle(booking.getFilm().getTitle());
		
		return dto;	
	}
	
	public BookingDTO mapToDTO(Booking b) {
		return this.mapper.map(b, BookingDTO.class);
	}
	
	public Booking mapFromDTO(BookingDTO b) {
		return this.mapper.map(b, Booking.class);
	}
	
	public BookingDTO create(BookingDTO b) {
		Booking in = this.mapFromDTO(b);
		Booking saved = this.repo.saveAndFlush(in);
		return this.mapToDTO(saved);
	}
	
	public Boolean delete(Long id ) {
		if (!this.repo.existsById(id)) {
			throw new IdNotFoundException();
		};
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
