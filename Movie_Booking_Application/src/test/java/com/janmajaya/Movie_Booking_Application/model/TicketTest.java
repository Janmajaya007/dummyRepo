package com.janmajaya.Movie_Booking_Application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TicketTest {

    @Test
    void testConstructor() {
        Ticket actualTicket = new Ticket();
        actualTicket.setBookedSeats(1L);
        actualTicket.setMovieName("Movie Name");
        actualTicket.setUsername("manish");
        actualTicket.setMovie_fk(1);
        actualTicket.setTheaterName("Theater Name");
        actualTicket.setTransactionId(1);
        assertEquals(1L, actualTicket.getBookedSeats());
        assertEquals("Movie Name", actualTicket.getMovieName());
        assertEquals("manish", actualTicket.getUsername());
        assertEquals(1, actualTicket.getMovie_fk());
        assertEquals("Theater Name", actualTicket.getTheaterName());
        assertEquals(1, actualTicket.getTransactionId());
    }


    @Test
    void testConstructor2() {
        Ticket actualTicket = new Ticket(1, "Movie Name","Theater Name",1L,"manish" , 1);

        assertEquals(1L, actualTicket.getBookedSeats());
        assertEquals(1, actualTicket.getTransactionId());
        assertEquals("Theater Name", actualTicket.getTheaterName());
        assertEquals(1, actualTicket.getMovie_fk());
        assertEquals("Movie Name", actualTicket.getMovieName());
        assertEquals("manish", actualTicket.getUsername());
    }
}

