package com.janmajaya.Movie_Booking_Application.service;


import com.janmajaya.Movie_Booking_Application.exception.DuplicateMovieIdException;
import com.janmajaya.Movie_Booking_Application.exception.MovieNotFoundException;
import com.janmajaya.Movie_Booking_Application.model.Movie;

import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie) throws DuplicateMovieIdException;

    List<Movie> getAllMovie();

    boolean deleteByMovieId(int id);

    boolean updateMovie(Integer id, Movie updatedMovie) throws MovieNotFoundException;

    Movie getMovieById(int bid) throws MovieNotFoundException;

    String ticketStatus(String movie);
}
