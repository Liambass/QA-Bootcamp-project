package com.liambass.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liambass.main.domain.Booking;
import com.liambass.main.repo.BookingRepo;
import com.liambass.main.repo.BookingWithFilmDTO;

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
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	private BookingWithFilmDTO mapToDTO(Booking booking) {
		BookingWithFilmDTO dto = new BookingWithFilmDTO();
		
		dto.setId(booking.getId());
		dto.setDate(booking.getDate());
		dto.setTime(booking.getTime());
		dto.setTitle(booking.getFilm().getTitle());
		
		return dto;
		
	}

}
