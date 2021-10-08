//package com.liambass.main.controller;
//
//import static org.junit.Assert.assertTrue;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.liambass.main.domain.Film;
//import com.liambass.main.dto.FilmDTO;
//import com.liambass.main.exceptions.IdNotFoundException;
//import com.liambass.main.exceptions.InvalidYearRangeException;
//import com.liambass.main.exceptions.NoMatchingRecordsException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//@ActiveProfiles("test")
//public class FilmControllerIntegrationTest {
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Autowired
//	private ObjectMapper mapper;
//	
//	private FilmDTO f1 = new FilmDTO(1L, "Test Film 1", "Genre", 2000, 90);
//	private FilmDTO f2 = new FilmDTO(2L, "Test Film 2", "Genre1", 2000, 120);
//	private FilmDTO f3 = new FilmDTO(3L, "Test Film 3", "Genre1", 2001, 99);
//	private List<FilmDTO> fl = List.of(f1, f2, f3);
//	
//	
//	@Test
//	public void createTest() throws Exception {
//		FilmDTO fIn = new FilmDTO("New", "Genre", 2020, 20);
//		FilmDTO fOut = new FilmDTO(4L, "New", "Genre", 2020, 20);
//		String fInAsJSON = this.mapper.writeValueAsString(fIn);
//		String fOutAsJSON = this.mapper.writeValueAsString(fOut);
//		
//		mvc.perform(post("/films/create")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(fInAsJSON))
//				.andExpect(status().isCreated())
//				.andExpect(content().json(fOutAsJSON));	
//	}
//	
//	
//	@Test
//	public void readAllTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		
//		mvc.perform(get("/films/readAll"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(flAsJSON));	
//	}
//	
//	@Test
//	public void readOneTest() throws Exception {
//		String f1AsJSON = this.mapper.writeValueAsString(f1);
//		
//		mvc.perform(get("/films/readOne/1"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(f1AsJSON));	
//	}
//	
//	@Test
//	public void readOneFailTest() throws Exception {
//		mvc.perform(get("/films/readOne/10"))
//		.andExpect(result -> assertTrue(result.getResolvedException() instanceof IdNotFoundException));
//	}
//	
//	@Test
//	public void updateTest() throws Exception {
//		FilmDTO fIn = new FilmDTO("New", "Genre", 2020, 20);
//		FilmDTO f1Up = new FilmDTO(1L, "New", "Genre", 2020, 20);
//		String fInAsJSON = this.mapper.writeValueAsString(fIn);
//		String f1UpAsJSON = this.mapper.writeValueAsString(f1Up);
//		
//		mvc.perform(put("/films/update/1")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(fInAsJSON))
//			.andExpect(status().isOk())
//			.andExpect(content().json(f1UpAsJSON));	
//	}
//	
//	@Test
//	public void updateFailTest() throws Exception {
//		Film fIn = new Film("New", "Genre", 2020, 20);
//		String fInAsJSON = this.mapper.writeValueAsString(fIn);
//		
//		mvc.perform(put("/films/update/10")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(fInAsJSON))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof IdNotFoundException));
//	}
//	
//	@Test
//	public void deleteTest() throws Exception {
//		mvc.perform(delete("/films/delete/1"))
//			.andExpect(status().isNoContent());
//
//	}
//	
//	@Test
//	public void deleteTestFail() throws Exception {
//		mvc.perform(delete("/films/delete/10"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof IdNotFoundException));
//	}
//	
//	@Test
//	public void findByTitleTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		
//		mvc.perform(get("/films/findByTitle/Test"))
//			.andExpect(status().isOk())
//			.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByTitleFailTest() throws Exception {
//		mvc.perform(get("/films/findByTitle/Fail"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//	
//	@Test
//	public void findByGenreTest() throws Exception {
//		List<FilmDTO> flGenre = List.of(f2, f3);
//		String flGenreAsJSON = this.mapper.writeValueAsString(flGenre);
//		
//		mvc.perform(get("/films/findByGenre/Genre1"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flGenreAsJSON));
//	}
//	
//	@Test
//	public void findByGenreFailTest() throws Exception {
//		mvc.perform(get("/films/findByGenre/Fail"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//	
//	@Test
//	public void findByYearTest() throws Exception {
//		List<FilmDTO> flYear = List.of(f1, f2);
//		String flYearAsJSON = this.mapper.writeValueAsString(flYear);
//		
//		mvc.perform(get("/films/findByYear/2000"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flYearAsJSON));
//	}
//	
//	@Test
//	public void findByYearFailTest() throws Exception {
//		mvc.perform(get("/films/findByYear/1990"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//	
//	@Test
//	public void findByYearRangeTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		
//		mvc.perform(get("/films/findByYearRange/2000/2020"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByYearRangeFailTest() throws Exception {
//		mvc.perform(get("/films/findByYearRange/1990/1991"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//	
//	@Test
//	public void findByYearRangeInvalidTest() throws Exception {
//		mvc.perform(get("/films/findByYearRange/1991/1990"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidYearRangeException));
//	}
//	
//	@Test
//	public void findByMaxDurationTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		
//		mvc.perform(get("/films/findByMaxDuration/120"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByMaxDurationFailTest() throws Exception {
//		mvc.perform(get("/films/findByMaxDuration/10"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//	
//	@Test
//	public void findByMinDurationTest() throws Exception {
//		String flAsJSON = this.mapper.writeValueAsString(fl);
//		
//		mvc.perform(get("/films/findByMinDuration/90"))
//		.andExpect(status().isOk())
//		.andExpect(content().json(flAsJSON));
//	}
//	
//	@Test
//	public void findByMinDurationFailTest() throws Exception {
//		mvc.perform(get("/films/findByMinDuration/1000"))
//			.andExpect(result -> assertTrue(result.getResolvedException() instanceof NoMatchingRecordsException));
//	}
//
//}
