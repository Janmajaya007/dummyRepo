package com.janmajaya.Movie_Booking_Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String movieName;
    private String theaterName;
    private long totalSeats = 100;
    private long availableSeats = 100;
    private long bookedSeats=0;
    private String movieImage;
    private double moviePrice;
    private String status="Book Ticket ASAP";

    @OneToMany(targetEntity= Ticket.class,cascade = CascadeType.ALL)
    @JoinColumn(name="movie_fk")
    private List<Ticket> ticketList;




}
