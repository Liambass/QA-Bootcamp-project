package com.liambass.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.liambass.main.domain.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long>{

//	@Query(value = "SELECT bookings.id, date, time, films.title AS film FROM bookings JOIN films ON bookings.film_id = films.id",
//			nativeQuery = true)
//	List<Booking> getBookings();
//	
	
	
	
	
}






