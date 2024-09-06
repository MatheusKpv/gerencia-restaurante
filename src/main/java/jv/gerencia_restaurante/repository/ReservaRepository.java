package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, ReservaRepositoryCustom {
    List<Reserva> findAllByObservacaoContainingIgnoreCase(String obs);
}
