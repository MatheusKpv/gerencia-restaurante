package jv.gerencia_restaurante.repository;

import jv.gerencia_restaurante.dto.PedidoFiltroDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepositoryCustom {
    Page<PedidoResponseDTO> getListaComFiltro(PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable);
}
