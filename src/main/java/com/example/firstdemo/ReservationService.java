package com.example.firstdemo;

import java.util.*;


import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository repository;
    private final Logger log = LoggerFactory.getLogger(ReservationService.class);


    public ReservationService(ReservationRepository repository){
        this.repository = repository;
    }



    public Reservation findReservationById(Long id) {
        Optional<ReservationEntity> opt = repository.findById(id);
        ReservationEntity reservation = opt.orElseThrow(() -> new EntityNotFoundException("There is no such id"));
        return toReservation(reservation);
    }


    public List<Reservation> findAllReservation(){
        return repository.findAll().stream().
                filter(res -> res.getStatus() != ReservationStatus.CANCELLED).
                map(this::toReservation).toList();
    }

    public Reservation createReservation(Reservation newReservation){
        if( newReservation.getId() != null || newReservation.getStatus() != null){
            throw new IllegalArgumentException("The user entered id or status");
        }
        var newReservationEntity = toReservationEntity(newReservation);
        newReservationEntity.setId(null);
        newReservationEntity.setReservationStatus(ReservationStatus.PENDING);
        return toReservation(repository.save(newReservationEntity));
    }

    public Reservation updateReservation(Long id, Reservation reservationToUpdate){
        if(reservationToUpdate.getId() != null || reservationToUpdate.getStatus() != null){
            throw new IllegalArgumentException("The user entered id or status");
        }
        if(id == null){
            throw new NullPointerException("This id null");
        }
        if(!repository.existsById(id)){
            throw new NoSuchElementException("User not found");
        }
        var reservationEntity = toReservationEntity(reservationToUpdate);
        reservationEntity.setId(id);
        reservationEntity.setReservationStatus(ReservationStatus.PENDING);
        return toReservation(repository.save(reservationEntity));

    }


    public void deleteReservation(Long id){
        var reservationEntity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        reservationEntity.setReservationStatus(ReservationStatus.CANCELLED);
        repository.save(reservationEntity);
    }

    public Reservation approvedReservation(Long id){
        var reservation = repository.findById(id).orElseThrow();
        if(reservation.getStatus() != ReservationStatus.PENDING ){
            throw new IllegalArgumentException("There is reservation already approved");
        }
        if(reservationConflict(toReservation(reservation))){
            throw new IllegalArgumentException("Conflict reservations");
        }
        reservation.setReservationStatus(ReservationStatus.APPROVED);
        return toReservation(repository.save(reservation));
    }

    private boolean reservationConflict(Reservation reservation){
        for(ReservationEntity existingReservation : repository.findAllByStatusIs(ReservationStatus.APPROVED)){
            if(Objects.equals(existingReservation.getId(), reservation.getId())){
                continue;
            }
            if(!Objects.equals(existingReservation.getRoomId(), reservation.getRoomId())){
                continue;
            }
            if(existingReservation.getStartDate().isBefore(reservation.getStartDate()) &&
                    reservation.getStartDate().isBefore(existingReservation.getEndDate()) ||
                    reservation.getEndDate().isBefore(existingReservation.getEndDate()) &&
                    existingReservation.getStartDate().isBefore(reservation.getStartDate())
            ) {
                return true;
            }
        }
        return false;
    }

    private Reservation toReservation(ReservationEntity reservation){
        return new Reservation(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getRoomId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus()
        );
    }
    private ReservationEntity toReservationEntity(Reservation reservation){
        return new ReservationEntity(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getRoomId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus()
        );
    }
}
