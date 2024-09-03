package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
