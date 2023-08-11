package com.janmajaya.Movie_Booking_Application.controller;


import com.janmajaya.Movie_Booking_Application.exception.DuplicateMovieIdException;
import com.janmajaya.Movie_Booking_Application.exception.MovieNotFoundException;
import com.janmajaya.Movie_Booking_Application.model.Movie;
import com.janmajaya.Movie_Booking_Application.producer.KafkaMessageProducer;
import com.janmajaya.Movie_Booking_Application.service.JwtService;
import com.janmajaya.Movie_Booking_Application.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class MovieController {


    @Autowired
    KafkaMessageProducer producer;
    @Autowired
    MovieService movieService;



    @Autowired
    JwtService jwtService;


    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String authorization) throws DuplicateMovieIdException {
    String role = jwtService.getRole(authorization.substring(7));
        if(!role.equalsIgnoreCase("ADMIN")) {
            return new ResponseEntity<String>("only admins are allowed to access the endpoint", HttpStatus.FORBIDDEN);
        }
        else if(movieService.addMovie(movie)!=null) {
            producer.pushMessage("Movie : " + movie.getMovieName() + " added");
            return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("movie is not created in DB", HttpStatus.CONFLICT);
    }

    @GetMapping("/getlAllMovies")
    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovie();
    }


    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable int movieId,@RequestHeader("Authorization") String authorization )
    {
        if(!jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("ADMIN")) {
            return new ResponseEntity<String>("only admins are allowed to access the endpoint", HttpStatus.FORBIDDEN);
        }
        else if(movieService.deleteByMovieId(movieId))
        {
            producer.pushMessage("Movie with ID " + movieId + " deleted");
            return new ResponseEntity<String>("Movie record deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Movie record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getMovieById/{movieId}")
    public Movie getMovieById(@PathVariable int movieId) throws MovieNotFoundException {
        return movieService.getMovieById(movieId);
    }


    @GetMapping("/checkMovieStatus/{name}")
    public String checkTicketStatus(@PathVariable String name)
    {
        return movieService.ticketStatus(name);
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id,@RequestBody Movie movie,@RequestHeader("Authorization") String authorization) throws MovieNotFoundException {
        if(!jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("ADMIN")) {
            return new ResponseEntity<String>("only admins are allowed to access the endpoint", HttpStatus.FORBIDDEN);
        }
        else if(movieService.updateMovie(id,movie))
        {
            producer.pushMessage("Movie with ID " + id + " deleted");
            return new ResponseEntity<String>("Movie updated succesfully ", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Movie is not updated", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
