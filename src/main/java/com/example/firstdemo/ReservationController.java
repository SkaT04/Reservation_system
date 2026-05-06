package com.example.firstdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    static private final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservationById(
            @PathVariable("id") Long id
    ){
        try{
            Reservation localReservation = reservationService.findReservationById(id);
            log.info("Called getReservationById: id = " + id);
            return ResponseEntity.status(HttpStatus.OK).body(localReservation);
        }catch(NoSuchElementException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservation(){
        log.info("Called getAllReservations");
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findAllReservation());
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation newReservation){
        try{
            Reservation localReservation = reservationService.createReservation(newReservation);
            log.info("New reservation build");
            return ResponseEntity.status(HttpStatus.CREATED).body(localReservation);
        }catch(IllegalArgumentException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservationToUpdate){
        try{
            Reservation localReservation = reservationService.updateReservation(id, reservationToUpdate);
            log.info("Update reservation id = {}",id);
            return ResponseEntity.status(HttpStatus.OK).body(localReservation);

        }catch(NoSuchElementException | IllegalArgumentException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id){
        try{
            reservationService.deleteReservation(id);
            log.info("Called deleteReservation: id = {}", id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(NoSuchElementException e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PatchMapping("/approved/{id}")
    public ResponseEntity<Reservation> approvedReservation(@PathVariable("id") Long id){
        try{
            Reservation localReservation = reservationService.approvedReservation(id);
            log.info("reservation approved: id = {}", id);
            return ResponseEntity.status(HttpStatus.OK).body(localReservation);
        }catch(NoSuchElementException e){
            log.info(e.getMessage());
            return ResponseEntity.status(404).build();
        }catch (IllegalArgumentException e){
            log.info(e.getMessage());
            return ResponseEntity.status(400).build();
        }

    }




}
