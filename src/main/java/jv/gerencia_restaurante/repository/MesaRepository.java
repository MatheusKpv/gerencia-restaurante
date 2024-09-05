package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Mesa;
import jv.gerencia_restaurante.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long>, MesaRepositoryCustom {
    Optional<Mesa> findTopNumeroByRestauranteOrderByNumeroDesc(Restaurante restaurante);
}
