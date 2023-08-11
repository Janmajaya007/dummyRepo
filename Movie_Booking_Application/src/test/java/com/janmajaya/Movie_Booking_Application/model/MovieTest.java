package com.janmajaya.Movie_Booking_Application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MovieTest {

    @Test
    void testConstructor() {
        Movie actualMovie = new Movie();
        actualMovie.setAvailableSeats(1L);
        actualMovie.setBookedSeats(1L);
        actualMovie.setId(1);
        actualMovie.setMovieName("Movie Name");
        actualMovie.setMovieImage("url");
        actualMovie.setMoviePrice(10.0d);
        actualMovie.setStatus("Status");
        actualMovie.setTheaterName("Theater Name");
        ArrayList<Ticket> ticketList = new ArrayList<>();
        actualMovie.setTicketList(ticketList);
        actualMovie.setTotalSeats(1L);
        assertEquals(1L, actualMovie.getAvailableSeats());
        assertEquals(1L, actualMovie.getBookedSeats());
        assertEquals(1, actualMovie.getId());
        assertEquals("Movie Name", actualMovie.getMovieName());
        assertEquals("url", actualMovie.getMovieImage());
        assertEquals(10.0d, actualMovie.getMoviePrice());
        assertEquals("Status", actualMovie.getStatus());
        assertEquals("Theater Name", actualMovie.getTheaterName());
        assertSame(ticketList, actualMovie.getTicketList());
        assertEquals(1L, actualMovie.getTotalSeats());
    }


    @Test
    void testConstructor2() {
        Movie actualMovie = new Movie(1, "Movie Name", "Theater Name", 1L, 1L, 1L,"url", 10.0d, "Status", new ArrayList<>());

        assertEquals(1L, actualMovie.getAvailableSeats());
        assertEquals(1L, actualMovie.getTotalSeats());
        assertTrue(actualMovie.getTicketList().isEmpty());
        assertEquals("Theater Name", actualMovie.getTheaterName());
        assertEquals("Status", actualMovie.getStatus());
        assertEquals(10.0d, actualMovie.getMoviePrice());
        assertEquals("Movie Name", actualMovie.getMovieName());
        assertEquals("url", actualMovie.getMovieImage());
        assertEquals(1, actualMovie.getId());
        assertEquals(1L, actualMovie.getBookedSeats());
    }
}

