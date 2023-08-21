package com.example.sistemadereserva.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="TB_RESERVATIONS")
public class ReservationModel implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReservation;
    private String nameClient;
    private int roomNumber;
    private Date dateInitialReservation;
    private Date dateFinalReservation;

    public UUID getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(UUID idReservation) {
        this.idReservation = idReservation;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getDateInitialReservation() {
        return dateInitialReservation;
    }

    public void setDateInitialReservation(Date dateInitialReservation) {
        this.dateInitialReservation = dateInitialReservation;
    }

    public Date getDateFinalReservation() {
        return dateFinalReservation;
    }

    public void setDateFinalReservation(Date dateFinalReservation) {
        this.dateFinalReservation = dateFinalReservation;
    }
}
