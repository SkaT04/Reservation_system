package com.example.firstdemo;


import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "reservations")
@Entity
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "roomId")
    private Long roomId;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;


    //@Enumerated(EnumType.STRING)
    @Column(name = "reservationStatus")
    private ReservationStatus reservationStatus;

    ReservationEntity(Long id, Long userId, Long roomId, LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus){
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationStatus = reservationStatus;

    }
    public ReservationEntity(){

    }

    public void setId(Long id){
        this.id = id;
    }

    public void setRoomId(Long roomId){
        this.roomId = roomId;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setReservationStatus(ReservationStatus reservationStatus){
        this.reservationStatus = reservationStatus;
    }

    public Long getId(){
        return id;
    }

    public Long getUserId(){
        return userId;
    }

    public Long getRoomId(){
        return roomId;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public ReservationStatus getStatus(){
        return reservationStatus;
    }
}
