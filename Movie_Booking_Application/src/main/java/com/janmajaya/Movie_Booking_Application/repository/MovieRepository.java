package com.janmajaya.Movie_Booking_Application.repository;


import com.janmajaya.Movie_Booking_Application.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    public Movie findByMovieNameAndTheaterName(String movieName,String theaterName);

    @Query(value="select availableSeats from Movie where movieName =?1")
    public long findAvailableSeatsByMovieName(String movieName);


}
