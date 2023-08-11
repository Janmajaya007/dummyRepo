package com.janmajaya.Movie_Booking_Application.service;

//import com.moviebookingapp.moviebookingapp.model.Ticket;

import com.janmajaya.Movie_Booking_Application.model.Ticket;

import java.util.List;

public interface TicketService {


    boolean bookMovie(Ticket ticket);

    boolean addTicket(Ticket ticket);


    boolean deleteTicketById(int id);

    List<Ticket> getAllTickets(int movieId);

    boolean deleteAllTicket(int movieId);
}
