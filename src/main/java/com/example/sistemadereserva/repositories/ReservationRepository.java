package com.example.sistemadereserva.repositories;

import com.example.sistemadereserva.models.ReservationModel;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, UUID> {
    List<ReservationModel> findByRoomNumber(int roomNumber);
}
