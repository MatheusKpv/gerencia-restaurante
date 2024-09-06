package jv.gerencia_restaurante.service.impl;

import jv.gerencia_restaurante.dto.PedidoFiltroDTO;
import jv.gerencia_restaurante.dto.PedidoRequestDTO;
import jv.gerencia_restaurante.dto.PedidoResponseDTO;
import jv.gerencia_restaurante.entity.Pedido;
import jv.gerencia_restaurante.entity.Reserva;
import jv.gerencia_restaurante.repository.PedidoRepository;
import jv.gerencia_restaurante.service.PedidoService;
import jv.gerencia_restaurante.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ReservaService reservaService;

    @Override
    public List<PedidoResponseDTO> getListaPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoResponseDTO::new).toList();
    }

    @Override
    public PedidoResponseDTO cadastraPedido(PedidoRequestDTO pedidoRequestDTO) {
        Reserva reserva = reservaService.findById(pedidoRequestDTO.idReserva());
        Pedido pedido = new Pedido(pedidoRequestDTO, reserva);
        pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    @Override
    public PedidoResponseDTO alteraPedido(Long id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = findById(id);
        Reserva reserva = null;
        if (pedidoRequestDTO.idReserva() != null) {
            reserva = reservaService.findById(pedidoRequestDTO.idReserva());
        }
        pedido.alteraDados(pedidoRequestDTO, reserva);
        pedidoRepository.save(pedido);
        return new PedidoResponseDTO(pedido);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Id do pedido não encontrado"));
    }

    @Override
    public Page<PedidoResponseDTO> getListaComFiltro(PedidoFiltroDTO pedidoFiltroDTO, Pageable pageable) {
        Page<PedidoResponseDTO> pedidos = pedidoRepository.getListaComFiltro(pedidoFiltroDTO, pageable);
        return pedidos;
    }
}
