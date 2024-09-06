package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.*;
import jv.gerencia_restaurante.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> getListaPedidos();

    PedidoResponseDTO cadastraPedido(PedidoRequestDTO pedidoRequestDTO);

    PedidoResponseDTO alteraPedido(Long id, PedidoRequestDTO pedidoRequestDTO);

    Pedido findById(Long id);

    Page<PedidoResponseDTO> getListaComFiltro(PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable);
}
