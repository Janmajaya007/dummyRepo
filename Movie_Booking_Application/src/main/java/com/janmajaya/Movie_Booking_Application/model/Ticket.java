package com.janmajaya.Movie_Booking_Application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String movieName;
    private String theaterName;
    private long bookedSeats;
    private String username;
    private int movie_fk;





}
