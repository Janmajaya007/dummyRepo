package com.janmajaya.Movie_Booking_Application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janmajaya.Movie_Booking_Application.model.Movie;
import com.janmajaya.Movie_Booking_Application.model.Ticket;
import com.janmajaya.Movie_Booking_Application.producer.KafkaMessageProducer;
import com.janmajaya.Movie_Booking_Application.service.JwtService;
import com.janmajaya.Movie_Booking_Application.service.serviceImpl.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @MockBean
    KafkaMessageProducer kafkaMessageProducer;

    @InjectMocks
    MovieController movieController;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieServiceImpl movieService;

    @MockBean
    JwtService jwtService;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    void getAllMovies() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);


//        given(movieService.addMovie(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
        when(movieService.getAllMovie()).thenReturn(List.of(movie));


        mockMvc.perform(get("/api/v1/getlAllMovies")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieName").value("WAR"))
                .andExpect(jsonPath("$[0].theaterName").value("PVR"))
                .andExpect(jsonPath("$[0].totalSeats").value(100))
                .andExpect(jsonPath("$[0].availableSeats").value(100))
                .andExpect(jsonPath("$[0].bookedSeats").value(0))
                .andExpect(jsonPath("$[0].moviePrice").value(120));

    }

    @Test
    void testGetMovieById() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.getMovieById(movie.getId())).willReturn(movie);

        mockMvc.perform(get("/api/v1/getMovieById/{movieId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MvcResult::getResponse);


    }


    @Test
    @Disabled
    void addMovie() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40,"manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40,"rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100L, 100L, 0L, "xyz",120D,"available", ticketList);
        given(movieService.addMovie(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
//        given(jwtService.getRole("Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NjU4ODgzMCwiZXhwIjoxNjg2NTkwNjMwfQ.kmf0uWgWqqYa5MEgufl9X1jjafcflu6ug4kSFijAUQc").equalsIgnoreCase("ADMIN")).willReturn(true);
        when(jwtService.getRole("Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NjU4ODgzMCwiZXhwIjoxNjg2NTkwNjMwfQ.kmf0uWgWqqYa5MEgufl9X1jjafcflu6ug4kSFijAUQc")).thenReturn("ADMIN");
//        given(jwtService.getRole("Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NjU4ODgzMCwiZXhwIjoxNjg2NTkwNjMwfQ.kmf0uWgWqqYa5MEgufl9X1jjafcflu6ug4kSFijAUQc").equalsIgnoreCase("ADMIN")).willReturn(true);
        String role = "ADMIN";
        Assertions.assertThat(role).isSameAs("ADMIN");
        mockMvc.perform(post("/api/v1/addMovie").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(movie)).header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY4NjU4ODgzMCwiZXhwIjoxNjg2NTkwNjMwfQ.kmf0uWgWqqYa5MEgufl9X1jjafcflu6ug4kSFijAUQc"))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.movieName").value("WAR"))
//                .andExpect(jsonPath("$.theaterName").value("PVR"))
//                .andExpect(jsonPath("$.totalSeats").value(100))
//                .andExpect(jsonPath("$.availableSeats").value(100))
//                .andExpect(jsonPath("$.bookedSeats").value(0))
//                .andExpect(jsonPath("$.moviePrice").value(120))
//                .andExpect(jsonPath("$.status").value("available"));


    }

    @Test
    @Disabled
    void addMovieFail() throws Exception {

        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);


        given(movieService.addMovie(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));
        mockMvc.perform(post("/api/addMovie").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isNotFound());
    }



    @Test
    @Disabled
    void deleteMovie() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.deleteByMovieId(movie.getId())).willReturn(true);

        mockMvc.perform(delete("/api/v1/delete/{movieId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MvcResult::getResponse);

    }

    @Test
    @Disabled
    void deleteMovieFail() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.deleteByMovieId(movie.getId())).willReturn(false);

        mockMvc.perform(delete("/api/v1/delete/{movieId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(500))
                .andExpect(MvcResult::getResponse);

    }

    @Test
    void checkTicketStatus() throws Exception {

        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.ticketStatus(movie.getMovieName())).willReturn("Book ASAP");

        mockMvc.perform(get("/api/v1/checkMovieStatus/{name}", "WAR")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }




    @Test
    @Disabled
    void testUpdateMovie() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.updateMovie(1, movie)).willReturn(true);
        mockMvc.perform(get("/api/v1/update", movie)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(405));

    }

    @Test
    @Disabled
    void testUpdateMovieFail() throws Exception {
        Ticket ticket1 = new Ticket(1, "WAR", "PVR", 40, "manish", 1);
        Ticket ticket2 = new Ticket(2, "WAR", "PVR", 40, "rahul", 1);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);
        Movie movie = new Movie(1, "WAR", "PVR", 100, 100, 0, "xyz.jpj", 120, "available", ticketList);

        given(movieService.updateMovie(1, movie)).willReturn(true);
        mockMvc.perform(put("/api/v1/update", movie)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));


    }
}