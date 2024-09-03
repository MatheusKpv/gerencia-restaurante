package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
