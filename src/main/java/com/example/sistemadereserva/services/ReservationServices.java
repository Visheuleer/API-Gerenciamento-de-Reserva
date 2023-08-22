package com.example.sistemadereserva.services;

import com.example.sistemadereserva.dtos.ReservationRecordDto;
import com.example.sistemadereserva.models.ReservationModel;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ReservationServices {
    public static boolean CheckOverlap(ReservationRecordDto rvPending,  List<ReservationModel> rvsMarked) {
        if(!rvsMarked.isEmpty()) {
            for (ReservationModel rvMarked : rvsMarked) {
                boolean overlaps = rvPending.dateInitialReservation().compareTo(rvMarked.getDateFinalReservation()) < 0
                        && rvPending.dateFinalReservation().compareTo(rvMarked.getDateInitialReservation()) > 0;

                boolean exactMatchStart = rvPending.dateInitialReservation().equals(rvMarked.getDateInitialReservation());
                boolean exactMatchEnd = rvPending.dateFinalReservation().equals(rvMarked.getDateFinalReservation());

                if(overlaps || exactMatchStart || exactMatchEnd){
                    return true;
                }
            }
        }

        return false;
    }
}
