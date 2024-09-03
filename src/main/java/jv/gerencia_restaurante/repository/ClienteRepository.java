package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
