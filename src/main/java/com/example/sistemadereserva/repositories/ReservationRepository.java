package com.example.sistemadereserva.repositories;

import com.example.sistemadereserva.models.ReservationModel;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, UUID> {
}
