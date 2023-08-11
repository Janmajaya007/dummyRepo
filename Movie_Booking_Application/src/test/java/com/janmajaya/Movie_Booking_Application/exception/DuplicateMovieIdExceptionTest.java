package com.janmajaya.Movie_Booking_Application.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DuplicateMovieIdExceptionTest {

    @Test
    void testConstructor() {
        DuplicateMovieIdException actualDuplicateMovieIdException = new DuplicateMovieIdException("An error occurred");
        assertNull(actualDuplicateMovieIdException.getCause());
        assertEquals(0, actualDuplicateMovieIdException.getSuppressed().length);
        assertEquals("An error occurred", actualDuplicateMovieIdException.getMessage());
        assertEquals("An error occurred", actualDuplicateMovieIdException.getLocalizedMessage());
    }
}

