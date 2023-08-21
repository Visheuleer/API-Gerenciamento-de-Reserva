package com.example.sistemadereserva.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ReservationRecordDto(@NotBlank String nameClient, @NotBlank int roomNumber, @NotBlank Date dateInitialReservation, @NotBlank Date dateFinalReservation) {

}
