package com.liambass.main.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liambass.main.domain.Film;
import com.liambass.main.service.FilmService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FilmControllerUnitTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private FilmService service;
	
	private Film f = new Film("Title", "Genre", 2021, 100);
	private List<Film> fl = List.of(f);
	private Long id = 1L;
	
	@Test
	public void createTest() throws Exception {
		String fAsJSON = this.mapper.writeValueAsString(f);
		Mockito.when(this.service.create(f)).thenReturn(f);
		
		mvc.perform(post("/films/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(fAsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(fAsJSON));	
	}
	
	@Test
	public void readAllTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		Mockito.when(this.service.readAll()).thenReturn(fl);
		
		mvc.perform(get("/films/readAll"))
			.andExpect(status().isOk())
			.andExpect(content().json(flAsJSON));	
	}
	
	@Test
	public void readOneTest() throws Exception {
		String fAsJSON = this.mapper.writeValueAsString(f);
		Mockito.when(this.service.readOne(id)).thenReturn(f);
		
		mvc.perform(get("/films/readOne/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(fAsJSON));	
	}
	
	@Test
	public void updateTest() throws Exception {
		String fAsJSON = this.mapper.writeValueAsString(f);
		Mockito.when(this.service.update(id, f)).thenReturn(f);
		
		mvc.perform(put("/films/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fAsJSON))
			.andExpect(status().isOk())
			.andExpect(content().json(fAsJSON));	
	}
	
	@Test
	public void deleteTest() throws Exception {
		Mockito.when(this.service.delete(id)).thenReturn(true, false);
		
		mvc.perform(delete("/films/delete/1"))
			.andExpect(status().isNoContent());
		
		mvc.perform(delete("/films/delete/1"))
			.andExpect(status().isBadRequest());
	}
	
	
}
