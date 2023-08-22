package com.example.sistemadereserva.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservationRecordDto(@NotBlank String nameClient, @NotNull int roomNumber, @NotNull Date dateInitialReservation, @NotNull Date dateFinalReservation) {

}
