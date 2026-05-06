package com.example.firstdemo;

import java.time.LocalDate;

public class Reservation {

    private Long id;
    private Long userId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    Reservation(Long id, Long userId, Long roomId, LocalDate startDate, LocalDate endDate, ReservationStatus status){
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;

    }
    Reservation(){

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

    public void setStatus(ReservationStatus status){
        this.status = status;
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
        return status;
    }

    @Override
    public String toString(){
        return "Reservation{" + "\n" +
                "Id = " + id + "\n" +
                "userId = " + userId + "\n" +
                "roomId = " + roomId + "\n" +
                "startDate = " + startDate + "\n" +
                "endDate = " + endDate + "\n" +
                "}";
    }

}
