package com.janmajaya.Movie_Booking_Application.repository;


import com.janmajaya.Movie_Booking_Application.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Query(value="select t from Ticket t where t.movie_fk = :Id")
    public List<Ticket> getAllTicketList(int Id);

    @Modifying
    @Query(value="delete from Ticket where movie_fk = :Id")
    public void deleteAllTicketData(int Id);
}
