package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustom {

}
