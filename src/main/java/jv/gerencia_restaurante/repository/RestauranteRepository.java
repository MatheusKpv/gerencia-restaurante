package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.MaiorFaturamentoMesRequestDTO;
import jv.gerencia_restaurante.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM pedido p WHERE p.reserva.id IN (" +
            "SELECT r.id FROM reserva r WHERE r.mesa.id IN (" +
            "SELECT m.id FROM mesa m WHERE m.restaurante.id = :id) AND r.dataReserva = :data)")
    BigDecimal findFaturamentoPorDia(@Param("id") Long id, @Param("data") LocalDate data);

    @Query("SELECT new jv.gerencia_restaurante.dto.MaiorFaturamentoMesRequestDTO(" +
            "r.dataReserva, COALESCE(SUM(p.valor), 0)) FROM pedido p " +
            "LEFT JOIN p.reserva r " +
            "LEFT JOIN r.mesa m " +
            "WHERE m.restaurante.id = :id AND EXTRACT(MONTH FROM r.dataReserva) = :mes " +
            "GROUP BY r.dataReserva " +
            "ORDER BY COALESCE(SUM(p.valor), 0) DESC")
    List<MaiorFaturamentoMesRequestDTO> findDatasMaiorFaturamentoMes(@Param("id") Long id, @Param("mes") Integer mes);
}
