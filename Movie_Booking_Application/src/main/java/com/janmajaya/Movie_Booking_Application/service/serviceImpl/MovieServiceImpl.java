package com.janmajaya.Movie_Booking_Application.service.serviceImpl;

//import com.moviebookingapp.moviebookingapp.exception.DuplicateMovieIdException;
//import com.moviebookingapp.moviebookingapp.exception.MovieNotFoundException;
//import com.moviebookingapp.moviebookingapp.model.Movie;
//import com.moviebookingapp.moviebookingapp.repository.MovieRepository;
//import com.moviebookingapp.moviebookingapp.service.MovieService;
//import com.moviebookingapp.moviebookingapp.service.TicketService;
import com.janmajaya.Movie_Booking_Application.exception.DuplicateMovieIdException;
import com.janmajaya.Movie_Booking_Application.exception.MovieNotFoundException;
import com.janmajaya.Movie_Booking_Application.model.Movie;
import com.janmajaya.Movie_Booking_Application.repository.MovieRepository;
import com.janmajaya.Movie_Booking_Application.service.MovieService;
import com.janmajaya.Movie_Booking_Application.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public Movie addMovie(Movie movie) throws DuplicateMovieIdException {
        Optional<Movie> Obj = movieRepository.findById(movie.getId());
        if(Obj.isPresent())
        {
            throw new DuplicateMovieIdException("movieId already in existence");
        }

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovie()
    {
        return movieRepository.findAll();
    }


    @Override
    public boolean deleteByMovieId(int id)
    {
        ticketService.deleteAllTicket(id);
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateMovie(Integer id, Movie updatedMovie) throws MovieNotFoundException {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setMovieName(updatedMovie.getMovieName());
            movie.setMoviePrice(updatedMovie.getMoviePrice());
            movie.setMovieImage(updatedMovie.getMovieImage());
            movie.setTheaterName(updatedMovie.getTheaterName());
            movieRepository.saveAndFlush(movie);
            return true;
        } else {
            throw new MovieNotFoundException("Movie not found with id: " + id);
        }
    }


       @Override
    public Movie getMovieById(int id) throws MovieNotFoundException {
        return this.movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie Not found"));

    }




    @Override
    public String ticketStatus(String movieName)
    {
        long count = movieRepository.findAvailableSeatsByMovieName(movieName);
        if(count!=0)
        {
            return "Book ASAP";
        }
        return "SOLD OUT";
    }


}
