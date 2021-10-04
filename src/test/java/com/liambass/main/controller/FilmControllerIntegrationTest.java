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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liambass.main.domain.Film;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FilmControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private Film f1 = new Film(1L, "Test Film 1", "Genre", 2000, 90);
	private Film f2 = new Film(2L, "Test Film 2", "Genre1", 2000, 120);
	private Film f3 = new Film(3L, "Test Film 3", "Genre1", 2001, 99);
	private List<Film> fl = List.of(f1, f2, f3);
	
	
	@Test
	public void createTest() throws Exception {
		Film fIn = new Film("New", "Genre", 2020, 20);
		Film fOut = new Film(4L, "New", "Genre", 2020, 20);
		String fInAsJSON = this.mapper.writeValueAsString(fIn);
		String fOutAsJSON = this.mapper.writeValueAsString(fOut);
		
		mvc.perform(post("/films/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(fInAsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(fOutAsJSON));	
	}
	
	
	@Test
	public void readAllTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		
		mvc.perform(get("/films/readAll"))
			.andExpect(status().isOk())
			.andExpect(content().json(flAsJSON));	
	}
	
	@Test
	public void readOneTest() throws Exception {
		String f1AsJSON = this.mapper.writeValueAsString(f1);
		
		mvc.perform(get("/films/readOne/1"))
			.andExpect(status().isOk())
			.andExpect(content().json(f1AsJSON));	
	}
	
	@Test
	public void updateTest() throws Exception {
		Film fIn = new Film("New", "Genre", 2020, 20);
		Film f1Up = new Film(1L, "New", "Genre", 2020, 20);
		String fInAsJSON = this.mapper.writeValueAsString(fIn);
		String f1UpAsJSON = this.mapper.writeValueAsString(f1Up);
		
		mvc.perform(put("/films/update/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fInAsJSON))
			.andExpect(status().isOk())
			.andExpect(content().json(f1UpAsJSON));	
	}
	
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/films/delete/1"))
			.andExpect(status().isNoContent());

	}
	
	@Test (expected = Exception.class)
	public void deleteTestFail() throws Exception {
		mvc.perform(delete("/films/delete/10"));

	}
	
	@Test
	public void findByTitleTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		
		mvc.perform(get("/films/findByTitle/Test"))
		.andExpect(status().isOk())
		.andExpect(content().json(flAsJSON));
	}
	
	@Test
	public void findByGenreTest() throws Exception {
		List<Film> flGenre = List.of(f2, f3);
		String flGenreAsJSON = this.mapper.writeValueAsString(flGenre);
		
		mvc.perform(get("/films/findByGenre/Genre1"))
		.andExpect(status().isOk())
		.andExpect(content().json(flGenreAsJSON));
	}
	
	@Test
	public void findByYearTest() throws Exception {
		List<Film> flYear = List.of(f1, f2);
		String flYearAsJSON = this.mapper.writeValueAsString(flYear);
		
		mvc.perform(get("/films/findByYear/2000"))
		.andExpect(status().isOk())
		.andExpect(content().json(flYearAsJSON));
	}
	
	@Test
	public void findByYearRangeTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		
		mvc.perform(get("/films/findByYearRange/2000/2020"))
		.andExpect(status().isOk())
		.andExpect(content().json(flAsJSON));
	}
	
	@Test
	public void findByMaxDurationTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		
		mvc.perform(get("/films/findByMaxDuration/120"))
		.andExpect(status().isOk())
		.andExpect(content().json(flAsJSON));
	}
	
	@Test
	public void findByMinDurationTest() throws Exception {
		String flAsJSON = this.mapper.writeValueAsString(fl);
		
		mvc.perform(get("/films/findByMinDuration/90"))
		.andExpect(status().isOk())
		.andExpect(content().json(flAsJSON));
	}

}
