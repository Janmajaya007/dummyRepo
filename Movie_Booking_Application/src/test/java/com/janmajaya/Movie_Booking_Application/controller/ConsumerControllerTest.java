package com.janmajaya.Movie_Booking_Application.controller;

import com.janmajaya.Movie_Booking_Application.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsumerControllerTest {



    @Test
    void testPerformLogin() throws Exception {
       
        ConsumerController consumerController = new ConsumerController();

        UserDTO userdto = new UserDTO();
        userdto.setPassword("password");
        userdto.setUsername("janedoe");
        ResponseEntity<?> actualPerformLoginResult = consumerController.performLogin(userdto);
        assertEquals("Login failed", actualPerformLoginResult.getBody());
        assertEquals(401, actualPerformLoginResult.getStatusCodeValue());
        assertTrue(actualPerformLoginResult.getHeaders().isEmpty());
    }



}

