package jv.gerencia_restaurante.dto;

import jv.gerencia_restaurante.entity.Pedido;
import jv.gerencia_restaurante.entity.Reserva;

import java.math.BigDecimal;

public record PedidoResponseDTO(
        Long id,
        //ReservaResponseDTO reserva,
        String nomePrato,
        BigDecimal valor
) {
    public PedidoResponseDTO (Pedido pedido) {
        this(pedido.getId(), pedido.getNomePrato(), pedido.getValor());
    }
}
