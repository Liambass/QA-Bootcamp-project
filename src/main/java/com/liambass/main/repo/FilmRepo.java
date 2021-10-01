package com.liambass.main.repo;


import org.springframework.stereotype.Repository;

import com.liambass.main.domain.Film;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long>{

}
