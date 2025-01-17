package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.PedidoFiltroDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import jv.gerencia_restaurante.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryCustom {
}
