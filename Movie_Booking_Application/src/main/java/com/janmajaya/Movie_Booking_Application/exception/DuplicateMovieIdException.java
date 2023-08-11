package com.janmajaya.Movie_Booking_Application.exception;

public class DuplicateMovieIdException extends Exception{

    public DuplicateMovieIdException(String message) {
        super(message);
    }
}
