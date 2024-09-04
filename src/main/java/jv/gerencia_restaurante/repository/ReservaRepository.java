package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByObservacaoContainingIgnoreCase(String obs);
}
