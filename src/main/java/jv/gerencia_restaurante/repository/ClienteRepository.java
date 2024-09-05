package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM cliente c ORDER BY c.quantidadeValorGasto DESC")
    List<Cliente> findClientesMaiorGasto();
}
