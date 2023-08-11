package com.janmajaya.Movie_Booking_Application.controller;


import com.janmajaya.Movie_Booking_Application.model.Ticket;
import com.janmajaya.Movie_Booking_Application.producer.KafkaMessageProducer;
import com.janmajaya.Movie_Booking_Application.service.JwtService;
import com.janmajaya.Movie_Booking_Application.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@CrossOrigin("*")
public class TicketController {

    @Autowired
    KafkaMessageProducer producer;

    @Autowired
    TicketService ticketService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/addticket")
    public ResponseEntity<?> addbookeMovie(@RequestBody Ticket ticket, @RequestHeader("Authorization") String authorization)
    {
        if (!jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("USER")) {
            return new ResponseEntity<>("Only users are allowed to access the endpoint", HttpStatus.FORBIDDEN);
        } else if (ticketService.bookMovie(ticket)) {
            producer.pushMessage("Movie "+ticket.getMovieName()+" booked");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Ticket is not created", HttpStatus.INTERNAL_SERVER_ERROR);
    }





    @GetMapping("/getAllTicket/{movieId}")
    public ResponseEntity<?> getAllTickets(@PathVariable int movieId){
        List<Ticket> allTicket=ticketService.getAllTickets(movieId);
        return ResponseEntity.ok(allTicket);
    }

//    @DeleteMapping("/deleteAllTicketsById/{movieId}")
//    public ResponseEntity<?> deleteAllTicket(@PathVariable int movieId,@RequestHeader("Authorization") String authorization )
//    {
//        if(!jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("ADMIN")) {
//            return new ResponseEntity<String>("only admins are allowed to access the endpoint", HttpStatus.FORBIDDEN);
//        }
//        else if(ticketService.deleteAllTicket(movieId))
//        {
//           	producer.pushMessage("Ticket with given movie id "+movieId+" deleted successfully");
//            return new ResponseEntity<String>("Ticket record deleted", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Ticket record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    @DeleteMapping("/deleteTicketsById/{id}")
    public ResponseEntity<?> deleteTicketById(@PathVariable int id,@RequestHeader("Authorization") String authorization )
    {
        if(!jwtService.getRole(authorization.substring(7)).equalsIgnoreCase("ADMIN")) {
            return new ResponseEntity<String>("only admins are allowed to access the endpoint", HttpStatus.FORBIDDEN);
        }
        else if(ticketService.deleteTicketById(id))
        {
	        	producer.pushMessage("Ticket with given id "+id+" deleted successfully");
            return new ResponseEntity<String>("Ticket record deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Ticket record not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
