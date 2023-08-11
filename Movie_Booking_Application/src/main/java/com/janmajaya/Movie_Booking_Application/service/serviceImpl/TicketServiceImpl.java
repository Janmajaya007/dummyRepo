package com.janmajaya.Movie_Booking_Application.service.serviceImpl;


import com.janmajaya.Movie_Booking_Application.model.Movie;
import com.janmajaya.Movie_Booking_Application.model.Ticket;
import com.janmajaya.Movie_Booking_Application.repository.MovieRepository;
import com.janmajaya.Movie_Booking_Application.repository.TicketRepository;
import com.janmajaya.Movie_Booking_Application.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MovieRepository movieRepository;


    @Override
    public boolean bookMovie(Ticket ticket)
    {
        Movie movie = movieRepository.findByMovieNameAndTheaterName(ticket.getMovieName(), ticket.getTheaterName());

        if (movie != null) {
            long available = movie.getAvailableSeats() - ticket.getBookedSeats();
            long bookedSeats = movie.getBookedSeats() + ticket.getBookedSeats();

            if (available < 0) {
                return false; // Can't purchase more tickets than available
            } else if (available == 0) {
                movie.setStatus("SOLD OUT");
            }

            movie.setBookedSeats(bookedSeats);
            movie.setAvailableSeats(available);

            ticket.setTransactionId(ticket.getTransactionId());
            ticket.setMovieName(movie.getMovieName());
            ticket.setTheaterName(ticket.getTheaterName());

            movieRepository.save(movie);
            ticketRepository.save(ticket);

            return true;
        }

        return false;
    }



    @Override
    public boolean addTicket(Ticket ticket)
    {
        ticketRepository.save(ticket);
        return true;
    }

    @Override
    public  boolean deleteTicketById(int id)
    {
        ticketRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Ticket> getAllTickets(int movieId)
    {
        return  ticketRepository.getAllTicketList(movieId);
    }

    @Override
    public boolean deleteAllTicket(int movieId) {
        ticketRepository.deleteAllTicketData(movieId);
        return true;
    }


}
