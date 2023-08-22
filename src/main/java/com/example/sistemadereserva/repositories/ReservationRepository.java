package com.example.sistemadereserva.repositories;

import com.example.sistemadereserva.models.ReservationModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, UUID> {
    List<ReservationModel> findByRoomNumber(int roomNumber);
}
