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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationModel>> getAllReservations(){
        return ResponseEntity.status(HttpStatus.OK).body(reservationRepository.findAll());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> getOneReservation(@PathVariable(value = "id") UUID id){
        Optional<ReservationModel> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Reserva não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(reservation.get());
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<?> putReservation(@PathVariable(value = "id") UUID id,
                                            @RequestBody @Valid ReservationRecordDto reservationRecordDto){

        Optional<ReservationModel> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Reserva não encontrada."));
        }
        var reservationModel = new ReservationModel();
        BeanUtils.copyProperties(reservationRecordDto, reservationModel);
        return ResponseEntity.status(HttpStatus.OK).body(reservationRepository.save(reservationModel));
    }
}
