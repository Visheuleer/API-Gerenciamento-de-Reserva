package com.example.sistemadereserva.controllers;

import com.example.sistemadereserva.dtos.ReservationRecordDto;
import com.example.sistemadereserva.models.ReservationModel;
import com.example.sistemadereserva.repositories.ReservationRepository;
import com.example.sistemadereserva.services.ReservationServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/reservations")
    public ResponseEntity<?> saveReservation(@RequestBody @Valid ReservationRecordDto reservationRecordDto){
        List<ReservationModel> rvsMarked = reservationRepository.findByRoomNumber(reservationRecordDto.roomNumber());

        if (ReservationServices.CheckOverlap(reservationRecordDto, rvsMarked)) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),"A data escolhida já está ocupada.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        var reservationModel = new ReservationModel();
        BeanUtils.copyProperties(reservationRecordDto, reservationModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationRepository.save(reservationModel));
    }
}
