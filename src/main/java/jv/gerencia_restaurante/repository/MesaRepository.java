package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    @Query("SELECT m FROM mesa m WHERE m.capacidadePessoas >= :qtdPessoas AND m.restaurante.id = :idRestaurante " +
            "AND m.id NOT IN (" +
            "SELECT r.mesa.id FROM reserva r WHERE r.dataReserva = :data AND r.status = AGENDADA)")
    List<Mesa> findMesasDisponiveisPorDataEQtdPessoas(@Param("idRestaurante") Long idRestaurante,
                                                      @Param("data") LocalDate data,
                                                      @Param("qtdPessoas") Integer qtdPessoas);

    Optional<Mesa> findTopNumeroByRestauranteOrderByNumeroDesc(Restaurante restaurante);
}
