package com.janmajaya.Movie_Booking_Application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserDTOTest {

    @Test
    void testConstructor() {
        UserDTO actualUserDTO = new UserDTO();
        actualUserDTO.setPassword("password");
        actualUserDTO.setUsername("manish");
        assertEquals("password", actualUserDTO.getPassword());
        assertEquals("manish", actualUserDTO.getUsername());
    }
}

