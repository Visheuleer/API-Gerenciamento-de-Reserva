package com.example.sistemadereserva.controllers;

import com.example.sistemadereserva.dtos.ReservationRecordDto;
import com.example.sistemadereserva.models.ReservationModel;
import com.example.sistemadereserva.repositories.ReservationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationModel> saveReservation(@RequestBody @Valid ReservationRecordDto reservationRecordDto){
        var reservationModel = new ReservationModel();
        BeanUtils.copyProperties(reservationRecordDto, reservationModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationRepository.save(reservationModel));
    }
}
