//package com.liambass.main.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.liambass.main.dto.FilmDTO;
//import com.liambass.main.service.FilmService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class FilmControllerUnitTest {
//
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private ObjectMapper mapper;
//	
//	@MockBean
//	private FilmService service;
//	
//	private FilmDTO f = new FilmDTO("Title", "Genre", 2021, 100);
//	private List<FilmDTO> fl = List.of(f);
//	private Long id = 1L;
//	
//	@Test
//	public void createTest() throws Exception {
//		String fAsJSON = this.mapper.writeValueAsString(f);
//		Mockito.when(this.service.create(f)).thenReturn(f);
//		System.out.println(fAsJSON);
//		mvc.perform(post("/films/create")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(fAsJSON))
//				.andExpect(status().isCreated())
//				.andExpect(content().json(fAsJSON));	
//	}
//	
//	@Test
//	public void readAllTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.readAll()).thenReturn(fl);
//		
//		mvc.perform(get("/films/readAll"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(flAsJSON));	
//	}
//	
//	@Test
//	public void readOneTest() throws Exception {
//		String fAsJSON = this.mapper.writeValueAsString(f);
//		Mockito.when(this.service.readOne(id)).thenReturn(f);
//		
//		mvc.perform(get("/films/readOne/1"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(fAsJSON));	
//	}
//	
//	@Test
//	public void updateTest() throws Exception {
//		String fAsJSON = this.mapper.writeValueAsString(f);
//		Mockito.when(this.service.update(id, f)).thenReturn(f);
//		
//		mvc.perform(put("/films/update/1")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(fAsJSON))
//			.andExpect(status().isOk())
//			.andExpect(content().json(fAsJSON));	
//	}
//	
//	@Test
//	public void deleteTest() throws Exception {
//		Mockito.when(this.service.delete(id)).thenReturn(true);
//		
//		mvc.perform(delete("/films/delete/1"))
//			.andExpect(status().isNoContent());
//	}
//	
//	@Test
//	public void findByTitleTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByTitle("X")).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByTitle/X"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByGenreTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByGenre("X")).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByGenre/X"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByYearTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByYear(2000)).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByYear/2000"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByYearRangeTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByYearRange(2000, 2020)).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByYearRange/2000/2020"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByMaxDurationTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByMaxDuration(100)).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByMaxDuration/100"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByMinDurationTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		Mockito.when(this.service.findByMinDuration(100)).thenReturn(fl);
//		
//		mvc.perform(get("/films/findByMinDuration/100"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//} 
