package com.liambass.main.repo;


import org.springframework.stereotype.Repository;

import com.liambass.main.domain.Film;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long>{

	@Query(value = "SELECT * FROM films WHERE title LIKE %?1%", nativeQuery = true)
	List<Film> findByTitle(String title);
	
	@Query(value = "SELECT * FROM films WHERE genre = ?1", nativeQuery = true)
	List<Film> findByGenre(String genre);
	
	@Query(value = "SELECT * FROM films WHERE year = ?1", nativeQuery = true)
	List<Film> findByYear(int year);
	
	@Query(value = "SELECT * FROM films WHERE year BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Film> findByYearRange(int year1, int year2);
	
	@Query(value = "SELECT * FROM films WHERE duration >= ?1", nativeQuery = true)
	List<Film> findByMinDuration(int duration);
	
	@Query(value = "SELECT * FROM films WHERE duration <= ?1", nativeQuery = true)
	List<Film> findByMaxDuration(int duration);
	
}
