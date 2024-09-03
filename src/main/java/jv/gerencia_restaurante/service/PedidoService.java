package jv.gerencia_restaurante.service;

import jv.gerencia_restaurante.dto.ClienteRequestDTO;
import jv.gerencia_restaurante.dto.ClienteResponseDTO;
import jv.gerencia_restaurante.dto.PedidoRequestDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import jv.gerencia_restaurante.entity.Pedido;

import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> getListaPedidos();

    PedidoResponseDTO cadastraPedido(PedidoRequestDTO pedidoRequestDTO);

    PedidoResponseDTO alteraPedido(Long id, PedidoRequestDTO pedidoRequestDTO);

    Pedido findById(Long id);
}
