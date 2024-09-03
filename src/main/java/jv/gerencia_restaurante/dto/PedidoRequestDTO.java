package jv.gerencia_restaurante.dto;

import java.math.BigDecimal;

public record PedidoRequestDTO(
        Long idReserva,
        String nomePrato,
        BigDecimal valor
) {
}
