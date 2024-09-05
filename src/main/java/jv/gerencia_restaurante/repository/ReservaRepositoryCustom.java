package jv.gerencia_restaurante.repository;

import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReservaRepositoryCustom {
    BigDecimal findTotalPorReserva(@Param("id") Long id);
}
